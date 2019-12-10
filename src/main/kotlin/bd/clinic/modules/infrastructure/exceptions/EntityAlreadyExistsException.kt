package bd.clinic.modules.infrastructure.exceptions

import kotlin.reflect.KClass

class EntityAlreadyExistsException(entityClass: KClass<*>, id: Int) :
        RuntimeException("${entityClass.simpleName} with id: $id already exists.")