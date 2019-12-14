package bd.clinic.modules.patient

import org.hibernate.validator.constraints.pl.PESEL
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Patient(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        val firstName: String,

        val lastName: String,

        @Column(updatable = false, unique = true)
        @field:PESEL(message = "Invalid PESEL number.")
        val pesel: String,

        val streetName: String,

        val buildingNumber: String,

        val zipCode: String,

        val city: String,

        @Column(updatable = false)
        val creationDate: LocalDateTime = LocalDateTime.now()
)