package commandManager.commands;


import IOManager.TerminalOutputModule;
import data.HumanBeing;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import main.Utilities;

import java.util.Scanner;
import java.util.Set;

public class ClearCommand extends BaseCommand{
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "clears all contents of the collection.";
    }

    @Override
    public String getArgs(){ return "";}

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();
        Set<Long> contents = hashtableManager.getKeys();
        contents.forEach((content) -> hashtableManager.getHumanBeingHashtable().remove(content));
        outputModule.writeByDefault("Command executed.");
    }
}
