package bd.clinic.modules.examinationInfo

import bd.clinic.modules.examinationInfo.ExaminationType.*

object ExaminationTypeTranslator {
    fun translateToPolish(type: ExaminationType) = when (type) {
        //BLOOD
        HAEMOGLOBIN -> "Hemoglobina"
        LEUKOCYTES -> "Leukocyty"
        PLATELETS -> "Płytki krwi"
        HAEMATOCRIT -> "Hematokryt"
        INR -> "INR"
        SODIUM -> "Sód"
        POTASSIUM -> "Potas"
        CHLORIDE -> "Chlorek"
        UREA_NITROGEN -> "Azot mocznikowy"
        OSMOLALITY_SERUM -> "Osmolalność surowicy"
        GLUCOSE -> "Glukoza"
        HbA1c -> "HbA1c"

        //URINE
        URIBILINOGEN -> "Urobilinogen"
        BILIRUBIN -> "Bilirubina"
        KETONE -> "Ketony"
        BLOOD -> "Krew"
        PROTEIN -> "Białko"
        PH -> "pH"

        //ALLERGY
        GRASS -> "Trawa"
        BIRCH_TREE -> "Brzoza"
        WORMWOOD -> "Bylica"
        CAT -> "Kot"
        DOG -> "Pies"
        CLADOSPORIUM -> "Cladosporium herbarum"
        ALTERNARIA -> "Alternaria alternata"
        EGG_WHITE -> "Białko jaja"
        EGG_YOLK -> "Żółtko jaja"
        MILK -> "Mleko"
        COD -> "Dorsz"
        CASEIN -> "Kazeina"
        WHEAT_FLOUR -> "Mąka pszenna"
        RICE -> "Ryż"
        SOY -> "Soja"
        PEANUT -> "Orzech ziemny"
        HAZELNUT -> "Orzech laskowy"
        CARROT -> "Marchewka"
        POTATO -> "Ziemniak"
        APPLE -> "Jabłko"
    }
}