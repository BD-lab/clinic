package bd.clinic.modules.order

import bd.clinic.modules.order.examinationResult.ExaminationResultDTO

data class OrderResultDTO(

        val orderNumber: String,

        val patientId: Int,

        val examinations: List<ExaminationResultDTO>
)