package bd.clinic.modules.address

import javax.persistence.Embeddable

@Embeddable
class Address(
        var streetName: String,
        var buildingNumber: String,
        var zipCode: String,
        var city: String
)