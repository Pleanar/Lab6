package commandManager.commands;

import IOManager.Server.MultiBox;
import data.HumanBeing;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import modules.HumanBeingModule;
import modules.Module;

import java.util.Scanner;
import java.util.Set;

public class RemoveLowerCommand extends BaseCommand{

    private Module<HumanBeing> handler;

    public RemoveLowerCommand()
    {
        handler = new HumanBeingModule();
    }

    @Override
    public MultiBox execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);

        HumanBeing person = handler.buildObject();
        MultiBox multiBox = new MultiBox("remove_lower");
        multiBox.addToBox(person);
        return multiBox;
    }
}