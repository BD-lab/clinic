package bd.clinic.modules.examinationInfo

enum class ExaminationType(val unit: String, val minNormValue: Double, val maxNormValue: Double) {
    //BLOOD
    HAEMOGLOBIN("g/L", 115.0, 148.0 ),
    LEUKOCYTES("*10^9 /L", 2.6, 7.8),
    PLATELETS("*10^9 /L", 130.0, 330.0),
    HAEMATOCRIT("", 0.34, 0.43),
    INR("", 1.0, 1.1),
    SODIUM("mmol/L", 136.0, 145.0),
    POTASSIUM("mmol/L", 3.4, 4.5),
    CHLORIDE("mmol/L", 98.0, 106.0),
    UREA_NITROGEN("mmol/L", 1.7, 8.3),
    OSMOLALITY_SERUM("mosmol/kg", 280.0, 300.0),
    GLUCOSE("mmol/L", 3.8, 6.4),
    HbA1c("%", 0.0, 6.0),

    //URINE
    URIBILINOGEN("mg/dL", 0.05, 2.5),
    BILIRUBIN("mg/dL", 0.0, 1.8),
    KETONE("mg/dL", 0.0, 300.0),
    BLOOD("Ery/uL", 0.0, 200.0),
    PROTEIN("mg/dL", 0.0, 2000.0),
    pH("", 5.0, 8.5),
}