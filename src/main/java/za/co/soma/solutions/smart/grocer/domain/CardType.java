package za.co.soma.solutions.smart.grocer.domain;

public enum CardType {

    VISA("VISA"),
    MASTER_CARD("MasterCard"),
    AMERICAN_EXPRESS("American Express");


    private String description;

    CardType(String description){
        this.description = description;

    }
}
