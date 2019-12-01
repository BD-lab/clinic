package bd.clinic.modules.patient

import bd.clinic.modules.infrastructure.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository
) {
    fun getAllPatients(): List<Patient> = patientRepository.findAll()

    fun getPatientOrElseThrow(patientId: Int): Patient = patientRepository.findById(patientId).orElseThrow {
        EntityNotFoundException(Patient::class, patientId)
    }

    fun addPatient(patient: Patient): Patient = patientRepository.save(patient)
}