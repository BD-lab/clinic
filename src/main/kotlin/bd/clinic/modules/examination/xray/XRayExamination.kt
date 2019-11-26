package bd.clinic.modules.examination.xray

import bd.clinic.modules.examination.Examination
import bd.clinic.modules.patient.domain.Patient
import javax.persistence.Entity

@Entity
class XRayExamination(patient: Patient, code: String) : Examination(patient = patient, code = code) {

}