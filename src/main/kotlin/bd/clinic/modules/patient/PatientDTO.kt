package bd.clinic.modules.patient

import bd.clinic.modules.address.Address
import org.hibernate.validator.constraints.pl.PESEL

data class PatientDTO(
        val id: Int? = null,
        val firstName: String,
        val lastName: String,
        @field:PESEL(message = "Invalid PESEL number.")
        val pesel: String,
        val address: Address
) {
    constructor(patient: Patient) : this(
            id = patient.id,
            firstName = patient.firstName,
            lastName = patient.lastName,
            pesel = patient.pesel,
            address = patient.address
    )

    fun toPatientEntity() = Patient(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            pesel = this.pesel,
            address = this.address
    )
}