package bd.clinic.modules.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Int> {
    fun findAllByPatientId(patientId: Int): List<Order>
    fun findFirstByOrderNumber(orderNumber: String): Order?

    @Query(
            value = "SELECT order_number FROM order_table",
            nativeQuery = true
    )
    fun findAllOrderNumbers(): List<String>
}