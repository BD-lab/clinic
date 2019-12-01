package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository
) {
    fun getAllPatients(): List<Patient> = patientRepository.findAll()

    fun getPatientOrElseThrow(patientId: Int): Patient = patientRepository.findByIdOrNull(patientId)
            ?: throw EntityNotFoundException(Patient::class, patientId)

    fun addPatient(patient: Patient): Patient = patientRepository.save(patient)
}