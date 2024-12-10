package exceptions;

public class ValidationException extends RuntimeException {

    // Constructor cu mesaj
    public ValidationException(String message) {
        super(message);
    }

    // Constructor cu mesaj și cauză
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
