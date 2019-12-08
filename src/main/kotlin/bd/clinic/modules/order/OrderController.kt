package bd.clinic.modules.order

import bd.clinic.modules.order.OrderController.Companion.ORDER_BASE_PATH
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ORDER_BASE_PATH)
class OrderController(
        private val orderService: OrderService
) {
    companion object {
        const val ORDER_BASE_PATH = "/orders"
        const val ORDER_PATIENT_PATH = "/patient"

        const val ORDER_ID = "orderId"
    }

    @GetMapping("/{$ORDER_ID}")
    fun getOrder(@PathVariable orderId: Int): OrderDTO = orderService.getOrder(orderId)

    @GetMapping
    fun getAllOrders(): List<OrderDTO> = orderService.getAllOrders()

    @GetMapping("$ORDER_PATIENT_PATH/{patientId}")
    fun getAllPatientOrders(@PathVariable patientId: Int): List<OrderDTO> =
            orderService.getAllPatientOrders(patientId)

    @PostMapping
    fun addPatientOrder(@RequestBody order: OrderDTO): OrderDTO = orderService.addPatientOrder(order)
}