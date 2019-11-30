package bd.clinic.modules.patient

import org.springframework.stereotype.Service

@Service
class PatientService(
        private val patientRepository: PatientRepository
)