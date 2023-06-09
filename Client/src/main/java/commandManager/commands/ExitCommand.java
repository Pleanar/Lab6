package commandManager.commands;

import IOManager.Server.MultiBox;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

public class ExitCommand extends BaseCommand {

    @Override
    public MultiBox execute(String[] args) throws WrongAmountOfArgumentsException, BuildObjectException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        System.exit(0);
        return new MultiBox<>("exit");
    }
}