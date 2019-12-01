package bd.clinic.modules.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass

@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityNotFoundException(entityClass: KClass<*>, id: Int) :
        RuntimeException("${entityClass.simpleName} with id: $id not found.")