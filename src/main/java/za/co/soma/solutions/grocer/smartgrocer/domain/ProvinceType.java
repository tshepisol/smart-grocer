package za.co.soma.solutions.grocer.smartgrocer.domain;

public enum ProvinceType {

    GAUTENG("Gauteng"),
    MPUMALANGA("Mpumalanga"),
    LIMPOPO("Limpopo"),
    NORTH_WEST("North West"),
    FREE_STATE("Free State"),
    EASTERN_CAPE("Eastern Cape"),
    WESTERN_CAPE("Western Cape"),
    NORTHERN_CAPE("Northern Cape"),
    KWA_ZULU_NATAL("Kwa-Zulu Natal");

    private String description;

   private ProvinceType(String description){
       this.description = description;
   }
}
