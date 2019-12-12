package bd.clinic.modules.order

data class OrderResultDTO(

        val orderNumber: String,

        val patientId: Int,

        val examinations: List<ExaminationResultDTO>
)