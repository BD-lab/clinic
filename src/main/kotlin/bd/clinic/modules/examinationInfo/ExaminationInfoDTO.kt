package bd.clinic.modules.examinationInfo

import bd.clinic.modules.restTemplate.LabConfig

class ExaminationInfoDTO(
        val id: Int? = null,
        val laboratoryId: Int,
        val type: ExaminationType
) {
    constructor(examinationInfo: ExaminationInfo) : this(
            id = examinationInfo.id,
            laboratoryId = examinationInfo.laboratoryId,
            type = examinationInfo.type
    )

    fun toExaminationEntity() = ExaminationInfo(
            id = this.id,
            laboratoryId = LabConfig.examinationLaboratoryMap.getValue(this.type),
            type = this.type
    )
}