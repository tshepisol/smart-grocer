package za.co.soma.solutions.smart.grocer.exception;

public class GrocerErrorType {
    private String errorMessage;

    public GrocerErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "GrocerErrorType{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
