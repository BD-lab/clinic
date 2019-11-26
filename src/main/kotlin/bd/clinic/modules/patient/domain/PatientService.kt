package bd.clinic.modules.patient.domain

import org.springframework.stereotype.Service

@Service
class PatientService(
    private val patientRepository: PatientRepository
)