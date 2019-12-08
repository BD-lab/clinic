package bd.clinic.modules.patientHistory

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patientHistory")
class PatientHistoryController(
        private val patientHistoryService: PatientHistoryService
) {

    @GetMapping
    fun getAllPatientsHistory(): List<PatientHistory> = patientHistoryService.getAllPatientsHistory()

    @GetMapping("/{patientId}")
    fun getPatientHistory(@PathVariable patientId: Int): List<PatientHistory> = patientHistoryService.getPatientHistory(patientId)

}