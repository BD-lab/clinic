package bd.clinic.modules.examination

import bd.clinic.modules.patient.Patient
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Examination(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        @ManyToOne
        val patient: Patient,
        val code: String
)