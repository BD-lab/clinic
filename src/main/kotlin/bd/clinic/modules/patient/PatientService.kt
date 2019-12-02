package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.EntityIdNullException
import bd.clinic.modules.infrastructure.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository
) {
    fun getAllPatients(): List<PatientDTO> = patientRepository.findAll().map { PatientDTO(it) }

    fun getPatientOrElseThrow(patientId: Int): PatientDTO = PatientDTO(
            patientRepository.findByIdOrNull(patientId)
                    ?: throw EntityNotFoundException(Patient::class, patientId)
    )

    fun addPatient(patient: PatientDTO): PatientDTO = PatientDTO(
            patientRepository.save(patient.toPatientEntity())
    )

    fun updatePatient(patient: PatientDTO): PatientDTO {
        val patientId = patient.id ?: throw EntityIdNullException(Patient::class)
        getPatientOrElseThrow(patientId)
        return PatientDTO(patientRepository.save(patient.toPatientEntity()))
    }
}