package bd.clinic.modules.infrastructure.exceptions

class OrderNotReadyException(orderNumber: String) :
        RuntimeException("Order with number: $orderNumber has not been completed yet.")