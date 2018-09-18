package za.co.soma.solutions.smart.grocer.domain;

public enum GenderType {

    MALE("Male"),
    FEMALE("Female");

    private String description;

    private GenderType(String description){
        this.description = description;
    }
}
