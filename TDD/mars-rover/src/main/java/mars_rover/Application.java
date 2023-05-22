package mars_rover;

import mars_rover.models.*;
import mars_rover.models.instruction.ObstacleInstruction;
import mars_rover.models.instruction.PlanetInstruction;
import mars_rover.models.instruction.RoverInstruction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.in;

public class Application {
    public static void main(final String[] args) {
        putTheEntriesFromTerminal();
    }

    private static void putTheEntriesFromTerminal() {
        Scanner scanner = new Scanner(in);
        String planetDimension = readPlanetDimension(scanner);
        Set<Obstacle> obstacles = readObstacles(scanner);
        Planet planet = new PlanetInstruction(planetDimension, obstacles).getPlanetFromDimension();
        String roverInitialPosition = readRoverInitialPosition(scanner);
        List<Command> commands = readRoverCommands(scanner);

        runRoverAndDisplayResults(planet, roverInitialPosition, commands);
    }

    private static String readPlanetDimension(Scanner scanner) {
        System.out.println("### Planet informations ###");
        System.out.println("Rule : A valid form is x y and it is numerics values");
        System.out.println("Give the planet dimension :");
        return scanner.nextLine();
    }

    private static Set<Obstacle> readObstacles(Scanner scanner) {
        System.out.println("\n### Obstacles on planet informations ###");
        System.out.println("Rule : A valid form is X1 Y1 X2 Y2...Xn Yn and it is numerics values");
        System.out.println("Give the obstacles positions :");
        String obstacleCoordinates = scanner.nextLine();
        return new ObstacleInstruction(obstacleCoordinates).getObstaclesFromInput();
    }

    private static String readRoverInitialPosition(Scanner scanner) {
        System.out.println("\n### Rover Initial position ###");
        System.out.println("Rule : A valid form is X Y D (X, Y for coordinates are numerics and D for Direction in [N,S,E,W])");
        System.out.println("Give the rover initial position");
        return scanner.nextLine();
    }

    private static List<Command> readRoverCommands(Scanner scanner) {
        System.out.println("\n### Rover Commands ###");
        System.out.println("Rule : A valid form is C1C2C3..Cn (Cx in [F,B,L,R]). Example : FBBLR");
        System.out.println("Give the rover commands");
        String commandsInput = scanner.nextLine();
        return Command.commandsFromCodes(commandsInput);
    }

    private static void runRoverAndDisplayResults(Planet planet,
                                                  String roverInitialPosition,
                                                  List<Command> commands) {
        Rover rover = new RoverInstruction(planet, roverInitialPosition, commands).getRoverFromInput();
        rover.processCommand();
        System.out.println("#### Final Result of Rover ####");
        System.out.println(rover.display());
    }
}
