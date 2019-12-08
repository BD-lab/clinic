package bd.clinic.modules.infrastructure.exceptions

class OrderAlreadyExistsException(orderNumber: String) :
        RuntimeException("Order with number: $orderNumber already exists.")