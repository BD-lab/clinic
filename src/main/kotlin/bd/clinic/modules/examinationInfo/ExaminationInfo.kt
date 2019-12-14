package bd.clinic.modules.examinationInfo

import javax.persistence.*

@Entity
class ExaminationInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        val laboratoryId: Int,

        @Enumerated(EnumType.STRING)
        val type: ExaminationType
)