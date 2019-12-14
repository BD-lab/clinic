package bd.clinic.modules.restTemplate

import bd.clinic.modules.examinationInfo.ExaminationType.*

object LabConfig {

    val laboratoryServerInfoMap = mapOf(1 to ServerInfo(8081, "127.0.0.1"))

    val examinationLaboratoryMap = mapOf(
            //BLOOD
            HAEMOGLOBIN to 1,
            LEUKOCYTES to 1,
            PLATELETS to 1,
            HAEMATOCRIT to 1,
            INR to 1,
            SODIUM to 1,
            POTASSIUM to 1,
            CHLORIDE to 1,
            UREA_NITROGEN to 1,
            OSMOLALITY_SERUM to 1,
            GLUCOSE to 1,
            HbA1c to 1,

            //URINE
            URIBILINOGEN to 2,
            BILIRUBIN to 2,
            KETONE to 2,
            BLOOD to 2,
            PROTEIN to 2,
            PH to 2,

            //ALLERGY
            GRASS to 3,
            BIRCH_TREE to 3,
            WORMWOOD to 3,
            CAT to 3,
            DOG to 3,
            CLADOSPORIUM to 3,
            ALTERNARIA to 3,
            EGG_WHITE to 3,
            EGG_YOLK to 3,
            MILK to 3,
            COD to 3,
            CASEIN to 3,
            WHEAT_FLOUR to 3,
            RICE to 3,
            SOY to 3,
            PEANUT to 3,
            HEZELNUT to 3,
            CARROT to 3,
            POTATO to 3,
            APPLE to 3)

    data class ServerInfo(val port: Int, val ipAddr: String)
}