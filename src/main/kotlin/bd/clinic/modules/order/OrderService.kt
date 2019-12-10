package bd.clinic.modules.order

import bd.clinic.modules.infrastructure.exceptions.EntityNotFoundException
import bd.clinic.modules.infrastructure.exceptions.OrderAlreadyExistsException
import bd.clinic.modules.infrastructure.exceptions.OrderWithNumberNotFoundException
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

    fun getOrderByNumber(orderNumber: String) = OrderDTO(
            orderRepository.findByOrderNumber(orderNumber) ?: throw OrderWithNumberNotFoundException(orderNumber)
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
        if (orderRepository.existsByOrderNumber(orderNumber))
            throw OrderAlreadyExistsException(orderNumber)
    }
}