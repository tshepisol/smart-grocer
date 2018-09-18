package za.co.soma.solutions.smart.grocer.domain;

public enum BankType {

    ABSA("ABSA"),
    CAPITEC("Capitec"),
    FNB("First National Bank"),
    STANDARDBANK("Standard Bank"),
    NEDBANK("Nedbank");

    private String description;

    BankType(String description){
        this.description = description;

    }
}
