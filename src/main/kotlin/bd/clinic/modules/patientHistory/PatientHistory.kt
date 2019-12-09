package bd.clinic.modules.patientHistory

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class PatientHistory(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        val patientId: Int,

        @Column(updatable = false)
        val modifiedOn: LocalDateTime = LocalDateTime.now(),

        val patientBeforeModify: String,

        val patientAfterModify: String
)