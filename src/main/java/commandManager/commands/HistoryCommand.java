package commandManager.commands;

import IOManager.TerminalOutputModule;
import commandManager.CommandHandler;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

public class HistoryCommand extends BaseCommand{
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescr() {
        return "shows last 12 commands used (without their arguments).";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        CommandHandler.getHistory().descendingIterator().forEachRemaining((command) -> new TerminalOutputModule().writeByDefault(command));
        outputModule.writeByDefault("Command executed.");
    }
}
