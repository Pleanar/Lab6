package hashtables.modules;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.Coordinates;
import data.validators.XCoordinateValidator;
import data.validators.YCoordinateValidator;
import exceptions.*;
import main.Utilities;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An implementation for the Module<Coordinates> that allows to build Coordinates object with user's input.
 */
public class CoordinatesModule implements Module<Coordinates> {
    private static OutputModule outputModule = new TerminalOutputModule();

    public void setOutputModule(OutputModule outputModule){
        CoordinatesModule.outputModule = outputModule;
    }
    @Override
    public Coordinates buildObject() throws BuildObjectException {
        OutputModule outputModule = new TerminalOutputModule();
        try {
            outputModule.writeByDefault("Generating object...");
            Coordinates result = new Coordinates();
            outputModule.writeByDefault("Welcome to master of Coordinates object creation!");
            outputModule.writeByDefault("Follow the instructions to setup your object.");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    XCoordinateValidator xValidator = new XCoordinateValidator();
                    outputModule.writeByDefault("Enter the value of x");
                    outputModule.writeInfo("(Type: Long)");
                    Long value = 0L;
                    if (Utilities.hasNextLineOrThrow(scanner)) {
                        String line = scanner.nextLine();
                        if (!line.isEmpty())
                            value = Long.parseLong(line);
                    }
                    if (xValidator.validate(value))
                        result.setX(value);
                    else {
                        outputModule.writeException("Value violates restrictions for field! Try again.");
                        outputModule.writeInfo("Restrictions: Long value.");
                        continue;
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    outputModule.writeException("Wrong input! Try again.");
                    continue;
                }
                break;
            }

            while (true) {
                try {
                    YCoordinateValidator yValidator = new YCoordinateValidator();
                    outputModule.writeByDefault("Enter the value of y");
                    outputModule.writeInfo("(Type: Float)");
                    Float value = null;
                    if (Utilities.hasNextLineOrThrow(scanner)) {
                        String line = scanner.nextLine();
                        if (!line.isEmpty())
                            value = Float.valueOf(line);
                    }
                    if (yValidator.validate(value))
                        result.setY(value);
                    else {
                        outputModule.writeException("Value violates restrictions for field! Try again.");
                        outputModule.writeInfo("Restrictions: Integer value.");
                        continue;
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    outputModule.writeException("Wrong input! Try again.");
                    continue;
                }
                break;
            }

            outputModule.writeByDefault("Coordinates setup completed!");

            return result;
        } catch (StreamInterruptedException e) {
            throw new BuildObjectException("During a build process an error has occurred: " + e.getMessage());
        }
    }
}
