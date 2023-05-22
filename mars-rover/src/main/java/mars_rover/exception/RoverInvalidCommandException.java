package mars_rover.exception;

public class RoverInvalidCommandException extends RuntimeException {
    public RoverInvalidCommandException(String message) {
        super(message);
    }
}
