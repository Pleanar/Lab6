package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

public class RemoveKeyCommand extends BaseCommand {

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by key (id).";
    }
    @Override
    public String getArgs() {
        return "id";
    }
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        HashtableManager<Long, HumanBeing> handler = HumanBeingManager.getInstance();

        Long finalId = Utilities.handleUserInputID(args[1]);
        if (finalId == null) return;

        handler.getHumanBeingHashtable().remove(finalId);

        outputModule.writeByDefault("Command executed");
    }
}
