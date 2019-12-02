package bd.clinic.modules.patient

import bd.clinic.modules.patient.PatientController.Companion.PATIENT_BASE_PATH
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(PATIENT_BASE_PATH)
class PatientController(
        private val patientService: PatientService
) {
    companion object {
        const val PATIENT_BASE_PATH = "/patients"

        const val PATIENT_ID = "patientId"
    }

    @GetMapping
    fun getAllPatients(): List<PatientDTO> = patientService.getAllPatients()

    @GetMapping("/{$PATIENT_ID}")
    fun getPatient(@PathVariable patientId: Int): PatientDTO = patientService.getPatient(patientId)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun addPatient(@Valid @RequestBody patient: PatientDTO): PatientDTO = patientService.addPatient(patient)

    @PutMapping
    fun updatePatient(@Valid @RequestBody patient: PatientDTO): PatientDTO = patientService.updatePatient(patient)
}