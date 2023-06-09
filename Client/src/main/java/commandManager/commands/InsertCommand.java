package commandManager.commands;

import IOManager.Server.MultiBox;
import data.HumanBeing;
import exceptions.WrongAmountOfArgumentsException;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import main.Utilities;
import modules.HumanBeingModule;
import modules.Module;

public class InsertCommand extends BaseCommand {
    private Module<HumanBeing> handler;
    public InsertCommand()
    {
        handler = new HumanBeingModule();
    }

    @Override
    public MultiBox execute(String[] args) throws BuildObjectException, CommandInterruptedException, WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);

        HumanBeing person = handler.buildObject();
        MultiBox multiBox = new MultiBox("insert");
        multiBox.addToBox(person);
        return multiBox;
    }
}