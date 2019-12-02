package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.EntityAlreadyExistsException
import bd.clinic.modules.infrastructure.EntityIdNullException
import bd.clinic.modules.infrastructure.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository
) {
    fun getAllPatients(): List<PatientDTO> = patientRepository.findAll().map { PatientDTO(it) }

    fun getPatient(patientId: Int): PatientDTO = PatientDTO(
            findPatientOrThrow(patientId)
    )

    fun addPatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id
        if (patientId != null && patientRepository.findByIdOrNull(patient.id) != null)
            throw EntityAlreadyExistsException(Patient::class, patient.id)
        return PatientDTO(patientRepository.save(patient.toPatientEntity()))
    }

    fun updatePatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id ?: throw EntityIdNullException(Patient::class)
        findPatientOrThrow(patientId)
        return PatientDTO(patientRepository.save(patient.toPatientEntity()))
    }

    private fun findPatientOrThrow(patientId: Int): Patient =
            patientRepository.findByIdOrNull(patientId) ?: throw EntityNotFoundException(Patient::class, patientId)

}