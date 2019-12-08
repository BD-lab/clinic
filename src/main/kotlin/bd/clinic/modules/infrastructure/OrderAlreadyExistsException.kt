package bd.clinic.modules.infrastructure

class OrderAlreadyExistsException(orderNumber: String) :
        RuntimeException("Order with number: $orderNumber already exists.")