package bd.clinic.modules.order

import bd.clinic.modules.order.examinationResult.ExaminationResultDTO
import bd.clinic.modules.patient.PatientDTO

data class OrderResultDTO(

        val orderNumber: String,

        val patient: PatientDTO,

        val examinations: List<ExaminationResultDTO>
)