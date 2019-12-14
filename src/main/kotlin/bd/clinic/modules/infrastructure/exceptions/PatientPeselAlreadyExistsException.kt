package bd.clinic.modules.infrastructure.exceptions

class PatientPeselAlreadyExistsException(pesel: String) : RuntimeException("Patient with PESEL: $pesel already exists.")