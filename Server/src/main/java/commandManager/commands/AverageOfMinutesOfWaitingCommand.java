package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

import java.util.Iterator;
import java.util.Scanner;

public class AverageOfMinutesOfWaitingCommand extends BaseCommand{
    @Override
    public String getName() {
        return "average_minutes_of_waiting";
    }

    @Override
    public String getDescr() {
        return "prints out average value of minutes of waiting of all HumanBeing values in the collection";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

        Integer count = hashtableManager.getValues().size();
        Float average = 0F;

        Iterator<HumanBeing> iterator= hashtableManager.getValues().iterator();
        while (iterator.hasNext()){
            Float personWainting = iterator.next().getMinutesOfWaiting();
            if (personWainting != null) average += personWainting;
        }

        outputModule.writeByDefault(String.valueOf(average/count));
    }
}
