package bd.clinic.modules.order

import bd.clinic.modules.infrastructure.exceptions.OrderAlreadyExistsException
import bd.clinic.modules.infrastructure.exceptions.OrderNotReadyException
import bd.clinic.modules.infrastructure.exceptions.OrderWithNumberNotFoundException
import bd.clinic.modules.patient.PatientService
import bd.clinic.modules.restTemplate.LabServiceClient
import org.springframework.stereotype.Service

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val patientService: PatientService,
        private val labServiceClient: LabServiceClient
) {

    fun getOrderByNumber(orderNumber: String): OrderResultDTO {
        val orderDTO = OrderDTO(orderRepository.findByOrderNumber(orderNumber)
                ?: throw OrderWithNumberNotFoundException(orderNumber)
        )
        val returnOrderResult = getOrderResultFromLaboratories(orderDTO)
        checkIfOrderContainsAllExaminations(returnOrderResult)

        return returnOrderResult
    }

    fun getAllOrders(): List<OrderDTO> = orderRepository.findAll().map { OrderDTO(it) }

    fun getAllPatientOrders(patientId: Int): List<OrderDTO> {
        patientService.findPatientOrThrow(patientId)
        return orderRepository.findAllByPatientId(patientId).map { OrderDTO(it) }
    }

    fun addPatientOrder(order: OrderDTO): OrderDTO {
        checkIfOrderNumberIsUnique(order.orderNumber)
        val patient = patientService.findPatientOrThrow(order.patientId)
        saveOrderInLaboratories(order)

        return OrderDTO(orderRepository.save(order.toOrderEntity(patient)))
    }

    private fun saveOrderInLaboratories(orderDTO: OrderDTO) {
        LabServiceClient.infrastructurePortMap.entries.parallelStream().forEach { (laboratoryId, port) ->
            val infrastructureOrder = orderDTO.copy(
                    examinations = orderDTO.examinations.filter { it.laboratoryId == laboratoryId })
            if (infrastructureOrder.examinations.isNotEmpty())
                labServiceClient.sendRequest(infrastructureOrder, port)
        }
    }

    private fun getOrderResultFromLaboratories(orderDTO: OrderDTO): OrderResultDTO {
        val examinationResultList = mutableListOf<ExaminationResultDTO>()
        val laboratoriesList = orderDTO.examinations.map { it.laboratoryId }.distinct()

        laboratoriesList.parallelStream().forEach {
            labServiceClient.sendRequest(orderDTO.orderNumber, LabServiceClient.infrastructurePortMap.getValue(it))
                    ?.let { examResult -> examinationResultList.addAll(examResult) }
        }

        return orderDTO.toOrderResultDTO(examinationResultList)
    }

    private fun checkIfOrderNumberIsUnique(orderNumber: String) {
        if (orderRepository.existsByOrderNumber(orderNumber))
            throw OrderAlreadyExistsException(orderNumber)
    }

    private fun checkIfOrderContainsAllExaminations(returnOrderResult: OrderResultDTO) {
        if (returnOrderResult.examinations.filter { it.isDone }.count() != returnOrderResult.examinations.size)
            throw OrderNotReadyException(returnOrderResult.orderNumber)
    }

}