package bd.clinic.modules.patient.dto

import bd.clinic.modules.patient.domain.Patient

data class PatientDTO(
    val firstName: String,
    val lastName: String
) {
    constructor(patient: Patient) : this(
        firstName = patient.firstName,
        lastName = patient.lastName
    )
}