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
    fun getAllPatients(): List<Patient> = patientService.getAllPatients()

    @GetMapping("/{$PATIENT_ID}")
    fun getPatient(@PathVariable patientId: Int): Patient = patientService.getPatientOrElseThrow(patientId)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun addPatient(@Valid @RequestBody patient: Patient): Patient = patientService.addPatient(patient)
}