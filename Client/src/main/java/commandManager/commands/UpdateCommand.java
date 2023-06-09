package commandManager.commands;

import IOManager.Server.MultiBox;
import data.HumanBeing;
import data.validators.IdValidator;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import modules.HumanBeingModule;
import modules.Module;

public class UpdateCommand extends BaseCommand{
    private Module<HumanBeing> handler;

    public UpdateCommand()
    {
        handler = new HumanBeingModule();
    }

    @Override
    public MultiBox execute(String[] args) throws WrongAmountOfArgumentsException, BuildObjectException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);

        HumanBeing person = handler.buildObject();
        MultiBox multiBox = new MultiBox("update");
        multiBox.addToBox(person);
        return multiBox;
    }
}