package bd.clinic.modules.patientHistory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientHistoryRepository : JpaRepository<PatientHistory, Int> {

    fun findAllByPatientId(patient_id: Int): List<PatientHistory>
}