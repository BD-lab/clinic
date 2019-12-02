package bd.clinic.modules.infrastructure

import kotlin.reflect.KClass

class EntityAlreadyExistsException(entityClass: KClass<*>, id: Int) :
        RuntimeException("${entityClass.simpleName} with id: $id already exists.")