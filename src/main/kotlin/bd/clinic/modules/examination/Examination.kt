package bd.clinic.modules.examination

import javax.persistence.*

@Entity
class Examination(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        val laboratoryId: Int,

        @Enumerated(EnumType.STRING)
        val type: ExaminationType,

        val isDone: Boolean = false
)