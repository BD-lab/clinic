package bd.clinic.modules.order

import bd.clinic.modules.examination.ExaminationType

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
