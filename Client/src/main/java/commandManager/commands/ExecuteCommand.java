package commandManager.commands;

import IOManager.Server.MultiBox;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import fileManager.ExecutionReader;
import main.Utilities;

public class ExecuteCommand extends BaseCommand{

    @Override
    public MultiBox execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 1);
        String path = args[1];
        MultiBox multiBox = new MultiBox(args[0]);
        multiBox.addToBox(ExecutionReader.readAndPackage(path));
        return multiBox;
    }
}