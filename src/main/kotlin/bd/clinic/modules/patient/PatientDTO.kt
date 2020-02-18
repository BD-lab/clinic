package bd.clinic.modules.patient

import bd.clinic.modules.common.decode
import bd.clinic.modules.common.encode
import org.hibernate.validator.constraints.pl.PESEL

data class PatientDTO(
        val id: Int? = null,
        val firstName: String,
        val lastName: String,
        @field:PESEL(message = "Invalid PESEL number.")
        val pesel: String,
        val streetName: String,
        val buildingNumber: String,
        val zipCode: String,
        val city: String
) {
    constructor(patient: Patient) : this(
            id = patient.id,
            firstName = patient.firstName,
            lastName = patient.lastName,
            pesel = decode(patient.pesel),
            streetName = patient.streetName,
            buildingNumber = patient.buildingNumber,
            zipCode = patient.zipCode,
            city = patient.city
    )

    fun toPatientEntity() = Patient(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            pesel = encode(this.pesel),
            streetName = this.streetName,
            buildingNumber = this.buildingNumber,
            zipCode = this.zipCode,
            city = this.city
    )
}