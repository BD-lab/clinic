package bd.clinic.modules.examination

import bd.clinic.modules.examination.xray.XRayExamination

data class ExaminationDTO(
    val patientId: Int?,
    val code: String,
    val type: Examination.Type
) {
    constructor(examination: Examination) : this(
        patientId = examination.patient.id,
        code = examination.code,
        type = examination.getType()
    )
}

private fun Examination.getType() = when (this) {
    is XRayExamination -> Examination.Type.X_RAY
    else -> Examination.Type.BLOOD_TEST
}