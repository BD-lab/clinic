package bd.clinic.modules.patient

import bd.clinic.modules.patient.domain.PatientService
import org.springframework.web.bind.annotation.RestController

@RestController
class PatientController(
    private val patientService: PatientService
)