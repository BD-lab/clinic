package bd.clinic.modules.examination

class ExaminationDTO(
        val id: Int? = null,
        val laboratoryId: Int,
        val type: ExaminationType,
        val isDone: Boolean = false
) {
    constructor(examination: Examination) : this(
            id = examination.id,
            laboratoryId = examination.laboratoryId,
            type = examination.type,
            isDone = examination.isDone
    )

    fun toExaminationEntity() = Examination(
            id = this.id,
            laboratoryId = this.laboratoryId,
            type = this.type,
            isDone = this.isDone
    )
}