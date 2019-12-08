package bd.clinic.modules.infrastructure.exceptions

import kotlin.reflect.KClass

class EntityNotFoundException(entityClass: KClass<*>, id: Int) :
        RuntimeException("${entityClass.simpleName} with id: $id not found.")