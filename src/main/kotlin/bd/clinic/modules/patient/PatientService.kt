package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.EntityAlreadyExistsException
import bd.clinic.modules.infrastructure.EntityIdNullException
import bd.clinic.modules.infrastructure.EntityNotFoundException
import bd.clinic.modules.patientHistory.PatientHistoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository,
        private val patientHistoryService: PatientHistoryService
) {
    fun getAllPatients(): List<PatientDTO> = patientRepository.findAll().map { PatientDTO(it) }

    fun getPatient(patientId: Int): PatientDTO = PatientDTO(
            findPatientOrThrow(patientId)
    )

    fun addPatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id
        if (patientId != null && patientRepository.findByIdOrNull(patientId) != null)
            throw EntityAlreadyExistsException(Patient::class, patientId)
        val savedPatient = PatientDTO(patientRepository.save(patient.toPatientEntity()))
        patientHistoryService.save(null, savedPatient)
        return savedPatient
    }

    fun updatePatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id ?: throw EntityIdNullException(Patient::class)
        val patientBeforeModification = getPatient(patientId)
        val modifiedPatient = PatientDTO(patientRepository.save(patient.toPatientEntity()))
        patientHistoryService.save(patientBeforeModification, modifiedPatient)
        return modifiedPatient
    }

    private fun findPatientOrThrow(patientId: Int): Patient =
            patientRepository.findByIdOrNull(patientId) ?: throw EntityNotFoundException(Patient::class, patientId)

}