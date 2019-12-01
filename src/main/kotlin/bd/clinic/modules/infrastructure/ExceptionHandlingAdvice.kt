package bd.clinic.modules.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlingAdvice {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFoundEntity(e: EntityNotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleNotValidArgument(e: MethodArgumentNotValidException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.bindingResult.fieldError?.defaultMessage)
    }
}