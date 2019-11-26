package bd.clinic.modules.patient.domain

import bd.clinic.modules.examination.Examination
import javax.persistence.*

@Entity
class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    @OneToMany(mappedBy = "patient")
    val examinations: MutableList<Examination>
)