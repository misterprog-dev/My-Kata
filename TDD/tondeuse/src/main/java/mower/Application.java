package mower;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Position;
import mower.services.MowerService;

import java.io.IOException;
import java.util.List;

public class Application {
    private static final String FILE_PATH = "src/main/resources/data/data.txt";

    public static void main(final String[] args) throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        String filePath = FILE_PATH;

        if (args != null && args.length >= 1) {
            filePath = args[0];
        }

        MowerService mowerService = new MowerService(filePath);
        List<Position> positions = mowerService.launchMowers();
        System.out.println("\n--- Affichage des positions finales des tondeuses ---");
        System.out.println(mowerService.getFinalMowersPositions(positions));
        System.out.println("-----------------------------------------------------\n");
    }
}
