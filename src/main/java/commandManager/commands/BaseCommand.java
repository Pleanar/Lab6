package commandManager.commands;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

import java.util.Scanner;

/**
 * An interface for classes that implement commands to work with the HashTableManager.
 */
public abstract class BaseCommand {
    /**
     * An outputModule used by all the commands to write.
     * Should be initialized in main class first.
     */
    protected static OutputModule outputModule = new TerminalOutputModule();

    public static void setOutputModule(OutputModule newOutputModule){
        outputModule = newOutputModule;
    }

    /**
     * A method that returns the name of the command.
     * @return String name of the command.
     */
    public abstract String getName();
    /**
     * A method that returns the description of the command.
     * @return String description of the command.
     */
    public abstract String getDescr();

    /**
     * A method that returns the arguments needed for command's execution.
     * @return String of all arguments.
     */
    public abstract String getArgs();

    /**
     * A method to execute the command.
     * @param args String[] of arguments that are provided for the command.
     * @throws IllegalArgumentException
     * @throws BuildObjectException
     * @throws WrongAmountOfArgumentsException
     * @throws CommandInterruptedException
     */
    public abstract void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException;
}