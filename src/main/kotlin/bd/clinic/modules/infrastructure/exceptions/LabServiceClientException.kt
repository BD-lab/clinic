package bd.clinic.modules.infrastructure.exceptions

class LabServiceClientException(override var message: String, cause: Throwable? = null) : RuntimeException(message, cause)