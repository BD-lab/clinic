package bd.clinic.modules.patient

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : JpaRepository<Patient, Int> {
    fun findByPesel(pesel: String): Patient?
    fun existsByPesel(pesel: String): Boolean
}