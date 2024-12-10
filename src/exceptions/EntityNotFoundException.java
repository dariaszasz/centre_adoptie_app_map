package exceptions;

public class EntityNotFoundException extends RuntimeException {

    // Constructor cu mesaj
    public EntityNotFoundException(String message) {
        super(message);
    }

    // Constructor cu mesaj și cauză
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
