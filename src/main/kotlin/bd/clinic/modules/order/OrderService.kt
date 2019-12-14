package bd.clinic.modules.order

import bd.clinic.modules.infrastructure.exceptions.OrderAlreadyExistsException
import bd.clinic.modules.infrastructure.exceptions.OrderNotReadyException
import bd.clinic.modules.infrastructure.exceptions.OrderWithNumberNotFoundException
import bd.clinic.modules.order.examinationResult.ExaminationResultDTO
import bd.clinic.modules.patient.PatientDTO
import bd.clinic.modules.patient.PatientService
import bd.clinic.modules.restTemplate.LabConfig
import bd.clinic.modules.restTemplate.LabServiceClient
import org.springframework.stereotype.Service
import print.PrintResults

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val patientService: PatientService,
        private val labServiceClient: LabServiceClient
) {
    fun getOrderResultByNumber(orderNumber: String): OrderResultDTO {
        val orderDTO = OrderDTO(orderRepository.findByOrderNumber(orderNumber)
                ?: throw OrderWithNumberNotFoundException(orderNumber)
        )
        val patientDTO = patientService.getPatient(orderDTO.patientId)
        val orderResult = getOrderResultFromLaboratories(orderDTO, patientDTO)
        checkIfOrderContainsAllDoneExaminations(orderResult)

        return orderResult
    }

    fun printOrderResult(orderResult: OrderResultDTO) {
        val printer = PrintResults(orderResult)
        printer.print()
    }

    fun getAllOrders(): List<OrderDTO> = orderRepository.findAll().map { OrderDTO(it) }

    fun getAllPatientOrders(patientId: Int): List<OrderDTO> {
        patientService.findPatientOrThrow(patientId)
        return orderRepository.findAllByPatientId(patientId).map { OrderDTO(it) }
    }

    fun addPatientOrder(order: OrderDTO): OrderDTO {
        checkIfOrderNumberIsUnique(order.orderNumber)
        val patient = patientService.findPatientOrThrow(order.patientId)

        return OrderDTO(orderRepository.save(order.toOrderEntity(patient))).also { saveOrderInLaboratories(it) }
    }

    private fun saveOrderInLaboratories(orderDTO: OrderDTO) {
        LabConfig.laboratoryServerInfoMap.entries.parallelStream().forEach { (laboratoryId, serverInfo) ->
            val infrastructureOrder = orderDTO.copy(
                    examinations = orderDTO.examinations.filter { it.laboratoryId == laboratoryId })
            if (infrastructureOrder.examinations.isNotEmpty())
                labServiceClient.sendRequest(infrastructureOrder, serverInfo.port, serverInfo.ipAddr)
        }
    }

    private fun getOrderResultFromLaboratories(orderDTO: OrderDTO, patientDTO: PatientDTO): OrderResultDTO {
        val examinationResultList = mutableListOf<ExaminationResultDTO>()
        val laboratoriesList = orderDTO.examinations.map { it.laboratoryId }.distinct()

        laboratoriesList.parallelStream().forEach {
            labServiceClient.sendRequest(orderDTO.orderNumber, (LabConfig.laboratoryServerInfoMap[it]
                    ?: error("Unknown port!")).port, (LabConfig.laboratoryServerInfoMap[it]
                    ?: error("Unknown ip address!")).ipAddr)
                    ?.let { examResult -> examinationResultList.addAll(examResult) }
        }

        return orderDTO.toOrderResultDTO(examinationResultList, patientDTO)
    }

    private fun checkIfOrderNumberIsUnique(orderNumber: String) {
        if (orderRepository.existsByOrderNumber(orderNumber))
            throw OrderAlreadyExistsException(orderNumber)
    }

    private fun checkIfOrderContainsAllDoneExaminations(orderResult: OrderResultDTO) {
        if (!orderResult.examinations.all { it.isDone })
            throw OrderNotReadyException(orderResult.orderNumber)
    }
}