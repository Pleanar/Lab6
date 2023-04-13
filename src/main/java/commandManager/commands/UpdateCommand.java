package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import hashtables.modules.HumanBeingModule;
import data.validators.IdValidator;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.modules.Module;
import main.Utilities;

public class UpdateCommand extends BaseCommand{
    private Module<HumanBeing> handler;

    public UpdateCommand()
    {
        handler = new HumanBeingModule();
    }

    public UpdateCommand(Module<HumanBeing> handler){this.handler = handler;}

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescr() {
        return "Updates the element in the collection, using provided id.";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException, BuildObjectException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 1);
        handler.setOutputModule(outputModule);

        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();
        Long id = Utilities.handleUserInputID(args[1]);
        if (id == null){
            throw new IllegalArgumentException("Id wasn't provided, command wasn't executed.");
        }
        if (!new IdValidator().validate(id)){
            HumanBeing person = handler.buildObject();
            person.setId(id);
            hashtableManager.addElementToHashtable(person.getId(), person);
        } else {
            throw new CommandInterruptedException("No object with such id exist, command wasn't executed.");
        }

        outputModule.writeByDefault("Command executed.");
    }
}
