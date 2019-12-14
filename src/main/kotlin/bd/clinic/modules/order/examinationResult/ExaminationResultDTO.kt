package bd.clinic.modules.order.examinationResult

import bd.clinic.modules.examinationInfo.ExaminationType

data class ExaminationResultDTO(

        val id: Int,

        val orderNumber: String,

        val examinationType: ExaminationType,

        val unit: String,

        val patientValue: Double,

        val minNormValue: Double,

        val maxNormValue: Double,

        val isDone: Boolean = false
)
