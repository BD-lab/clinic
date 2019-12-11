package bd.clinic.modules.order

import bd.clinic.modules.examination.ExaminationDTO
import bd.clinic.modules.infrastructure.exceptions.EntityNotFoundException
import bd.clinic.modules.infrastructure.exceptions.OrderAlreadyExistsException
import bd.clinic.modules.infrastructure.exceptions.OrderNotReadyException
import bd.clinic.modules.infrastructure.exceptions.OrderWithNumberNotFoundException
import bd.clinic.modules.patient.PatientService
import bd.clinic.modules.restTemplate.LabServiceClient
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val patientService: PatientService,
        private val labServiceClient: LabServiceClient
) {
    fun getOrder(orderId: Int) = OrderDTO(
            orderRepository.findByIdOrNull(orderId) ?: throw EntityNotFoundException(Order::class, orderId)
    )

    fun getOrderByNumber(orderNumber: String): OrderDTO {
        val orderDTO = OrderDTO(orderRepository.findByOrderNumber(orderNumber)
                ?: throw OrderWithNumberNotFoundException(orderNumber)
        )
        val returnOrder = sendGetRequestToAllLaboratories(orderDTO)
        if (checkIfOrderContainsAllExaminations(returnOrder))
            return returnOrder

        throw OrderNotReadyException(orderNumber)
    }

    fun getAllOrders(): List<OrderDTO> = orderRepository.findAll().map { OrderDTO(it) }

    fun getAllPatientOrders(patientId: Int): List<OrderDTO> {
        patientService.findPatientOrThrow(patientId)
        return orderRepository.findAllByPatientId(patientId).map { OrderDTO(it) }
    }

    fun addPatientOrder(order: OrderDTO): OrderDTO {
        checkIfOrderNumberIsUnique(order.orderNumber)
        val patient = patientService.findPatientOrThrow(order.patientId)
        sendPostRequestToAllLaboratories(order)

        return OrderDTO(orderRepository.save(order.toOrderEntity(patient)))
    }

    private fun sendPostRequestToAllLaboratories(orderDTO: OrderDTO) {
        LabServiceClient.infrastructurePortMap.forEach { (laboratoryId, port) ->
            val infrastructureOrder = orderDTO.copy(
                    examinations = orderDTO.examinations.filter { it.laboratoryId == laboratoryId })
            if (infrastructureOrder.examinations.isNotEmpty())
                labServiceClient.sendRequest(infrastructureOrder, port)
        }
    }

    private fun sendGetRequestToAllLaboratories(orderDTO: OrderDTO): OrderDTO {
        val examinationResultList = mutableListOf<ExaminationDTO>()
        val laboratoriesList = orderDTO.examinations.map { it.laboratoryId }.distinct()

        laboratoriesList.forEach {
            labServiceClient.sendRequest(orderDTO.orderNumber, LabServiceClient.infrastructurePortMap.getValue(it))
                    ?.examinations?.let { examResult -> examinationResultList.addAll(examResult) }
        }

        return orderDTO.copy(examinations = examinationResultList)
    }

    private fun checkIfOrderNumberIsUnique(orderNumber: String) {
        if (orderRepository.existsByOrderNumber(orderNumber))
            throw OrderAlreadyExistsException(orderNumber)
    }

    private fun checkIfOrderContainsAllExaminations(returnOrder: OrderDTO): Boolean {
        return returnOrder.examinations.filter { it.isDone }.count() == returnOrder.examinations.size
    }

}