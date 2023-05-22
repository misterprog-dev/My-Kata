package mars_rover.models;

import mars_rover.exception.RoverInvalidCommandException;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public enum Command {
    FORWARD("F"),
    BACKWARD("B"),
    RIGHT("R"),
    LEFT("L");
    public static final String ROVER_COMMANDS_SEPARATOR = "";
    private final String code;

    Command(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static List<Command> commandsFromCodes(String codes) {
        return stream(codes.split(ROVER_COMMANDS_SEPARATOR))
                .map(Command::fromCode)
                .collect(toList());
    }

    public static Command fromCode(String code) {
        Optional<Command> command = stream(values())
                .filter(value -> value.getCode().equalsIgnoreCase(code))
                .findFirst();
        return command.orElseThrow(() -> new RoverInvalidCommandException("Invalid initial position"));
    }
}
