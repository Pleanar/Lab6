package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

public class ExitCommand extends BaseCommand{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "exits the program without saving.";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        System.exit(0);
    }
}
