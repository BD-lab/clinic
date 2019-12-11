package bd.clinic.modules.order

import bd.clinic.modules.examination.Examination
import bd.clinic.modules.patient.Patient
import javax.persistence.*

@Entity
@Table(name = "order_table")
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(unique = true)
        val orderNumber: String,

        @ManyToOne
        val patient: Patient,

        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "order_id")
        val examinations: List<Examination>
)