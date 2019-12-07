package bd.clinic.modules.patientHistory

import bd.clinic.modules.infrastructure.EntityNotFoundException
import bd.clinic.modules.patient.Patient
import bd.clinic.modules.patient.PatientDTO
import bd.clinic.modules.patient.PatientRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientHistoryService(
        private val patientHistoryRepository: PatientHistoryRepository,
        private val patientRepository: PatientRepository
) {
    fun getAllPatientsHistory(): List<PatientHistory> = patientHistoryRepository.findAll()

    fun getHistoryForPatient(patientId: Int): List<PatientHistory> {
        findPatientOrThrow(patientId)
        return patientHistoryRepository.findAllByPatientId(patientId)
    }

    fun save(patientBeforeModify: PatientDTO?, patientAfterModify: PatientDTO): PatientHistory {
        return patientHistoryRepository.save(
                PatientHistory(
                        patient = patientAfterModify.toPatientEntity(),
                        patientBeforeModification = patientBeforeModify.toString(),
                        patientAfterModification = patientAfterModify.toString())
        )
    }

    private fun findPatientOrThrow(patientId: Int): Patient =
            patientRepository.findByIdOrNull(patientId) ?: throw EntityNotFoundException(Patient::class, patientId)


}