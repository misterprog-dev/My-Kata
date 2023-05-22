package mars_rover.exception;

public class RoverEmptyCommandException extends RuntimeException {
    public RoverEmptyCommandException(String message) {
        super(message);
    }
}
