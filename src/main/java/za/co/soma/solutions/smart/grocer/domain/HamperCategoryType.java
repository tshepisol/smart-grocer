package za.co.soma.solutions.smart.grocer.domain;

public enum HamperCategoryType {

    EDUCATION("Education"),
    GROCERIES("Groceries"),
    CATERING("Catering"),
    HOUSEHOLD("Household");



    private String description;

    HamperCategoryType(String description){
        this.description = description;

    }
}
