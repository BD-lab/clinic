package bd.clinic.modules.infrastructure.exceptions

import java.lang.RuntimeException

class CannotConnectToLaboratoryException : RuntimeException("Cannot connect to laboratory!")