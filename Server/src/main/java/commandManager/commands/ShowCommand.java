package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import main.Utilities;
import java.util.Iterator;

public class ShowCommand extends BaseCommand {
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "Shows information about the values in the collection.";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        HashtableManager<Long, HumanBeing> humanBeingManager = HumanBeingManager.getInstance();
        Iterator<HumanBeing> handler = humanBeingManager.getValues().iterator();
        while (handler.hasNext()){
            outputModule.writeByDefault(handler.next().toString());
        }
        outputModule.writeByDefault("Command executed");
    }
}