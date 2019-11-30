package bd.clinic.modules.examination

import bd.clinic.modules.examination.roentgen.RoentgenExamination

data class ExaminationDTO(
        val patientId: Int?,
        val code: String,
        val type: ExaminationType
) {
    constructor(examination: Examination) : this(
            patientId = examination.patient.id,
            code = examination.code,
            type = examination.getType()
    )
}

private fun Examination.getType() = when (this) {
    is RoentgenExamination -> ExaminationType.ROENTGEN
    else -> ExaminationType.BLOOD_TEST
}