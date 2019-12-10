package bd.clinic.modules.patientHistory

import bd.clinic.modules.infrastructure.exceptions.EntityIdNullException
import bd.clinic.modules.infrastructure.exceptions.EntityNotFoundException
import bd.clinic.modules.patient.Patient
import bd.clinic.modules.patient.PatientDTO
import bd.clinic.modules.patient.PatientRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientHistoryService(
        private val patientHistoryRepository: PatientHistoryRepository,
        private val patientRepository: PatientRepository
) {
    fun getAllPatientsHistory(): List<PatientHistory> = patientHistoryRepository.findAll()

    fun getPatientHistory(patientId: Int): List<PatientHistory> {
        findPatientOrThrow(patientId)
        return patientHistoryRepository.findAllByPatientId(patientId)
    }

    fun save(patientBeforeModify: PatientDTO? = null, patientAfterModify: PatientDTO): PatientHistory {
        return patientHistoryRepository.save(
                PatientHistory(
                        patientId = patientAfterModify.id ?: throw EntityIdNullException(Patient::class),
                        patientBeforeModify = ObjectMapper().writeValueAsString(patientBeforeModify),
                        patientAfterModify = ObjectMapper().writeValueAsString(patientAfterModify))
        )
    }

    private fun findPatientOrThrow(patientId: Int): Patient =
            patientRepository.findByIdOrNull(patientId) ?: throw EntityNotFoundException(Patient::class, patientId)


}