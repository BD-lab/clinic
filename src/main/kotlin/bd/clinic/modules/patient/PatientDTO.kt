package bd.clinic.modules.patient

import bd.clinic.modules.examination.ExaminationDTO

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