package za.co.soma.solutions.grocer.smartgrocer.exception;

public class GrocerErrorType {
    private String errorMessage;

    public GrocerErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
