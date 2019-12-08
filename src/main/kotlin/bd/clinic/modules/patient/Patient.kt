package bd.clinic.modules.patient

import bd.clinic.modules.address.Address
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
        @field:PESEL(message = "Invalid PESEL number.")
        val pesel: String,
        @Embedded
        val address: Address,
        @Column(updatable = false)
        val creationDate: LocalDateTime = LocalDateTime.now()
)