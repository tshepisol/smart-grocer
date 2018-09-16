package za.co.soma.solutions.grocer.smartgrocer.domain;

public enum GenderType {

    MALE("Male"),
    FEMALE("Female");

    private String description;

    private GenderType(String description){
        this.description = description;
    }
}
