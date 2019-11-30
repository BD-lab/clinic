package bd.clinic.modules.patient

import org.springframework.web.bind.annotation.RestController

@RestController
class PatientController(
        private val patientService: PatientService
)