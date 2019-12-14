package bd.clinic.modules.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
        private val orderService: OrderService
) {
    @GetMapping("/result/{orderNumber}")
    fun getOrderResult(@PathVariable orderNumber: String): OrderResultDTO =
            orderService.getOrderResultByNumber(orderNumber)

    @GetMapping
    fun getAllOrders(): List<OrderDTO> = orderService.getAllOrders()

    @GetMapping(params = ["patientId"])
    fun getAllPatientOrders(@RequestParam("patientId") patientId: Int): List<OrderDTO> =
            orderService.getAllPatientOrders(patientId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPatientOrder(@RequestBody order: OrderDTO): OrderDTO = orderService.addPatientOrder(order)
}