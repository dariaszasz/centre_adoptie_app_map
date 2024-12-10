package exceptions;

public class DatabaseException extends RuntimeException {

    // Constructor cu mesaj
    public DatabaseException(String message) {
        super(message);
    }

    // Constructor cu mesaj și cauză
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
