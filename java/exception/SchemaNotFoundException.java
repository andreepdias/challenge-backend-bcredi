package exception;

public class SchemaNotFoundException extends RuntimeException {

    public SchemaNotFoundException(String message) {
        super(message);
    }
}
