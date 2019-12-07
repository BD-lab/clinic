package bd.clinic.modules.patientHistory

import bd.clinic.modules.patient.Patient
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class PatientHistory(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "patient_id")
        val patient: Patient,

        @Column(updatable = false)
        val modifiedOn: LocalDateTime = LocalDateTime.now(),

        val patientBeforeModification: String,

        val patientAfterModification: String
)