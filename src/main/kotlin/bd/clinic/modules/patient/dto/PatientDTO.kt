package bd.clinic.modules.patient.dto

import bd.clinic.modules.examination.ExaminationDTO
import bd.clinic.modules.patient.domain.Patient

data class PatientDTO(
    val firstName: String,
    val lastName: String,
    val examinations: List<ExaminationDTO>
) {
    constructor(patient: Patient) : this(
        firstName = patient.firstName,
        lastName = patient.lastName,
        examinations = patient.examinations.map { ExaminationDTO(it) }
    )
}