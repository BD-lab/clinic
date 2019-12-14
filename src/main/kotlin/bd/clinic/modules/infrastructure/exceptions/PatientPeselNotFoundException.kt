package bd.clinic.modules.infrastructure.exceptions

class PatientPeselNotFoundException(pesel: String) : RuntimeException("Patient with PESEL: $pesel not found.")