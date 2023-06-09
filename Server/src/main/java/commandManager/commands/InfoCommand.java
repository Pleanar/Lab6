package commandManager.commands;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.HumanBeing;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import main.Utilities;

public class InfoCommand extends BaseCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "Shows information about the collection.";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        HashtableManager<Long, HumanBeing> humanBeingManager = HumanBeingManager.getInstance();

        outputModule.writeByDefault("Now you are operating with collection of type " + humanBeingManager.getHumanBeingHashtable().getClass().getName() + ", filled with elements of type " + humanBeingManager.getValueType());
        outputModule.writeByDefault("Size of the collection is " + humanBeingManager.getValues().size());
        outputModule.writeByDefault("Init date: " + humanBeingManager.getInitDate());
    }
}

