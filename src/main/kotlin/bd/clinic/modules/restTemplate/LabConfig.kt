package bd.clinic.modules.restTemplate

import bd.clinic.modules.examinationInfo.ExaminationType.*

class LabConfig {

    companion object {

        val laboratoryServerInfoMap = mapOf(1 to ServerInfo(8081, "127.0.0.1"))

        val examinationLaboratoryMap = mapOf(
                ROENTGEN to 1,
                BLOOD_HORMONE_TEST to 1,
                BLOOD_GLUCOSE_TEST to 1,
                HEMOGLOBIN_TEST to 1,
                BLOOD_THYROID_TEST to 1)
    }

    data class ServerInfo(val port: Int, val ipAddr: String)
}