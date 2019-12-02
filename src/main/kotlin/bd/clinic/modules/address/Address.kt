package bd.clinic.modules.address

import javax.persistence.Embeddable

@Embeddable
data class Address(
        val streetName: String,
        val buildingNumber: String,
        val zipCode: String,
        val city: String
)