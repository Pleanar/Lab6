package commandManager.commands;

import IOManager.OutputModule;
import IOManager.Server.MultiBox;
import IOManager.Terminal.TerminalOutputModule;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

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
     * A method to execute the command.
     * @param args String[] of arguments that are provided for the command.
     * @throws IllegalArgumentException
     * @throws BuildObjectException
     * @throws WrongAmountOfArgumentsException
     * @throws CommandInterruptedException
     */
    public abstract MultiBox execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException;
}