package bd.clinic.modules.order

import bd.clinic.modules.examinationInfo.ExaminationInfoDTO
import bd.clinic.modules.infrastructure.exceptions.EntityIdNullException
import bd.clinic.modules.order.examinationResult.ExaminationResultDTO
import bd.clinic.modules.patient.Patient
import bd.clinic.modules.patient.PatientDTO

data class OrderDTO(
        val id: Int? = null,
        val orderNumber: String,
        val patientId: Int,
        val examinations: List<ExaminationInfoDTO>
) {
    constructor(order: Order) : this(
            id = order.id,
            orderNumber = order.orderNumber,
            patientId = order.patient.id ?: throw EntityIdNullException(Patient::class),
            examinations = order.examinations.map { ExaminationInfoDTO(it) }
    )

    fun toOrderEntity(patient: Patient) = Order(
            id = this.id,
            orderNumber = this.orderNumber,
            patient = patient,
            examinations = this.examinations.map { it.toExaminationEntity() }
    )

    fun toOrderResultDTO(examinations: List<ExaminationResultDTO>, patientDTO: PatientDTO) = OrderResultDTO(
            orderNumber = this.orderNumber,
            patient = patientDTO,
            examinations = examinations
    )
}