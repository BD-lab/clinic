package bd.clinic.modules.order

import bd.clinic.modules.infrastructure.EntityNotFoundException
import bd.clinic.modules.infrastructure.OrderAlreadyExistsException
import bd.clinic.modules.patient.PatientService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val patientService: PatientService
) {
    fun getOrder(orderId: Int) = OrderDTO(
            orderRepository.findByIdOrNull(orderId) ?: throw EntityNotFoundException(Order::class, orderId)
    )

    fun getAllOrders(): List<OrderDTO> = orderRepository.findAll().map { OrderDTO(it) }

    fun getAllPatientOrders(patientId: Int): List<OrderDTO> {
        patientService.findPatientOrThrow(patientId)
        return orderRepository.findAllByPatientId(patientId).map { OrderDTO(it) }
    }

    fun addPatientOrder(order: OrderDTO): OrderDTO {
        checkIfOrderNumberIsUnique(order.orderNumber)
        val patient = patientService.findPatientOrThrow(order.patientId)
        return OrderDTO(orderRepository.save(order.toOrderEntity(patient)))
    }

    private fun checkIfOrderNumberIsUnique(orderNumber: String) {
        val orderNumbers = orderRepository.findAllOrderNumbers()
        if (orderNumbers.contains(orderNumber))
            throw OrderAlreadyExistsException(orderNumber)
    }
}