package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import java.util.Set;

public class RemoveLowerKeyCommand extends BaseCommand{
    @Override
    public String getName() {
        return "remove_lower_key";
    }

    @Override
    public String getDescr() {
        return "removes all elements with lower keys (id numbers)";
    }

    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException{

        Utilities.checkArgumentsOrThrow(args.length, 1);

        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

        Long finalId = Utilities.handleUserInputID(args[1]);
        if (finalId == null) return;

        Set<Long> delete_keys = hashtableManager.getKeys();
        hashtableManager.getKeys().forEach((key) -> {if (key >= finalId){delete_keys.remove(key);}});
        delete_keys.forEach((delete_key) -> hashtableManager.getHumanBeingHashtable().remove(delete_key));

        outputModule.writeByDefault("Command executed");
    }
}
