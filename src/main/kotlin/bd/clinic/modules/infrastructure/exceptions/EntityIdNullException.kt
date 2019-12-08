package bd.clinic.modules.infrastructure.exceptions

import kotlin.reflect.KClass

class EntityIdNullException(entityClass: KClass<*>) :
        RuntimeException("Id of ${entityClass.simpleName} cannot be null.")