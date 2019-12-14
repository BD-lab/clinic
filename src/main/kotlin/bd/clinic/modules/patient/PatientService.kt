package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.exceptions.*
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

    fun getPatientByPesel(pesel: String): PatientDTO = PatientDTO(
            patientRepository.findByPesel(pesel) ?: throw PatientPeselNotFoundException(pesel)
    )

    fun addPatient(patient: PatientDTO): PatientDTO {
        checkIfPatientAlreadyExists(patient)
        return PatientDTO(patientRepository.save(patient.toPatientEntity())).also {
            patientHistoryService.save(patientAfterModify = it)
        }
    }

    fun updatePatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id ?: throw EntityIdNullException(Patient::class)
        val patientBeforeModification = getPatient(patientId)
        val modifiedPatient = PatientDTO(patientRepository.save(patient.toPatientEntity()))
        patientHistoryService.save(patientBeforeModification, modifiedPatient)
        return modifiedPatient
    }

    fun findPatientOrThrow(patientId: Int): Patient =
            patientRepository.findByIdOrNull(patientId) ?: throw EntityNotFoundException(Patient::class, patientId)

    private fun checkIfPatientAlreadyExists(patient: PatientDTO) {
        val patientId = patient.id
        if (patientId != null && patientRepository.findByIdOrNull(patientId) != null)
            throw EntityAlreadyExistsException(Patient::class, patientId)

        val pesel = patient.pesel
        if (patientRepository.existsByPesel(pesel))
            throw PatientPeselAlreadyExistsException(pesel)
    }
}