package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import java.util.Set;

public class RemoveAllByMinutesOfWaitingCommand extends BaseCommand{
    @Override
    public String getName() {
        return "remove_all_by_minutes_of_waiting";
    }

    @Override
    public String getDescr() {
        return "removes all HumanBeing objects of the collection that have the provided value of minutes of waiting";
    }

    @Override
    public String getArgs() {
        return "minutes of waiting (Type: Float)";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

        Float minutesOfWaiting;

        if (!Utilities.isNotNumeric(args[1])){
            minutesOfWaiting = Float.valueOf(args[1]);
        } else {
            if (args[1].equalsIgnoreCase("null")) minutesOfWaiting = null;
            else throw new IllegalArgumentException("Requirements aren't met. Should be Float.");
        }

        Set<Long> delete_keys = hashtableManager.getKeys();
        if (minutesOfWaiting != null) hashtableManager.getValues().forEach((human) -> { if (!human.getMinutesOfWaiting().equals(minutesOfWaiting)) {delete_keys.remove(human.getId());}});
        if (minutesOfWaiting == null) hashtableManager.getValues().forEach((human) -> { if (!(human.getMinutesOfWaiting() == null)) {delete_keys.remove(human.getId());}});
        delete_keys.forEach((delete_key) -> hashtableManager.getHumanBeingHashtable().remove(delete_key));

        outputModule.writeByDefault("Command executed");
    }
}
