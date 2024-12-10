package exceptions;

public class BusinessLogicException extends RuntimeException {

    // Constructor cu mesaj
    public BusinessLogicException(String message) {
        super(message);
    }

    // Constructor cu mesaj și cauză
    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
