package bd.clinic.modules.examination.roentgen

import bd.clinic.modules.examination.Examination
import bd.clinic.modules.patient.Patient
import javax.persistence.Entity

@Entity
class RoentgenExamination(patient: Patient, code: String) : Examination(patient = patient, code = code) {

}