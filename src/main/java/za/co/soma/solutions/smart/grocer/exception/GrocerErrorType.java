package za.co.soma.solutions.smart.grocer.exception;

public class GrocerErrorType {
    private String errorMessage;

    public GrocerErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
