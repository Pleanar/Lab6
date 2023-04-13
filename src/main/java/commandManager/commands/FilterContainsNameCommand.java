package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

import java.util.Arrays;
import java.util.Iterator;

public class FilterContainsNameCommand extends BaseCommand{
    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String getDescr() {
        return "prints out all the elements in the collection that contain given string in their name value";
    }

    @Override
    public String getArgs() {
        return "name (Type: String)";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

        StringBuilder name = new StringBuilder("");
        Iterator<String> input_name= Arrays.stream(args).iterator();
        input_name.next();
        while (input_name.hasNext()){
            name.append(input_name.next());
        }

        hashtableManager.getValues().forEach((humanBeing) -> {
            if (humanBeing.getName().contains(name)){
                outputModule.writeByDefault(humanBeing.toString());
            }
        });

        outputModule.writeByDefault("Command executed.");
    }
}
