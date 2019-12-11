package bd.clinic.modules.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class LabServiceClientException(override var message: String, cause: Throwable? = null) : RuntimeException(message, cause)