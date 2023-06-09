package main;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import exceptions.StreamInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

import java.util.Scanner;

public class Utilities {
    private static OutputModule outputModule = new TerminalOutputModule();
    public static void setOutputModule(OutputModule outputModule){
        Utilities.outputModule = outputModule;
    }
    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static Long handleUserInputID(String input) {
        if (Utilities.isNotNumeric(input)) {
            outputModule.writeException("Provided argument id: \"" + input + "\" is not a number.");
            return null;
        }

        Long id = null;
        try {
            id = Long.valueOf(input);
        } catch (NumberFormatException e) {
            outputModule.writeException("Provided argument: \"" + input + "\" is too large for ID field.");
        }
        return id;
    }

    public static void checkArgumentsOrThrow(int provided, int expected) throws WrongAmountOfArgumentsException {
        if (provided - 1 != expected)
            throw new WrongAmountOfArgumentsException("Provided " + (provided - 1) + " arguments, expected " + expected);
    }

    public static boolean hasNextLineOrThrow(Scanner scanner) throws StreamInterruptedException {
        if (scanner.hasNextLine()) return true;
        else throw new StreamInterruptedException("The input stream was unexpectedly stopped.");
    }
}
