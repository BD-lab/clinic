package bd.clinic.modules.patient

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/patients")
class PatientController(
        private val patientService: PatientService
) {
    @GetMapping
    fun getAllPatients(): List<PatientDTO> = patientService.getAllPatients()

    @GetMapping("/{patientId}")
    fun getPatient(@PathVariable patientId: Int): PatientDTO = patientService.getPatient(patientId)

    @GetMapping(params = ["pesel"])
    fun getPatientByPesel(@RequestParam("pesel") pesel: String): PatientDTO
            = patientService.getPatientByPesel(pesel)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun addPatient(@Valid @RequestBody patient: PatientDTO): PatientDTO = patientService.addPatient(patient)

    @PutMapping
    fun updatePatient(@Valid @RequestBody patient: PatientDTO): PatientDTO = patientService.updatePatient(patient)
}