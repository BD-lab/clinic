package bd.clinic.modules.examinationInfo

class ExaminationInfoDTO(
        val id: Int? = null,
        val laboratoryId: Int,
        val type: ExaminationType,
        val isDone: Boolean = false
) {
    constructor(examinationInfo: ExaminationInfo) : this(
            id = examinationInfo.id,
            laboratoryId = examinationInfo.laboratoryId,
            type = examinationInfo.type,
            isDone = examinationInfo.isDone
    )

    fun toExaminationEntity() = ExaminationInfo(
            id = this.id,
            laboratoryId = this.laboratoryId,
            type = this.type,
            isDone = this.isDone
    )
}