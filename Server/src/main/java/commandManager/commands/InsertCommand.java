package commandManager.commands;

import IOManager.TerminalOutputModule;
import data.HumanBeing;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import hashtables.modules.HumanBeingModule;
import data.validators.IdValidator;
import data.validators.Validator;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import hashtables.modules.Module;
import main.Utilities;
import java.util.Scanner;

public class InsertCommand extends BaseCommand {
    private Module<HumanBeing> handler;
    public InsertCommand()
    {
        handler = new HumanBeingModule();
    }
    public InsertCommand(Module<HumanBeing> handler)
    {
        this.handler = handler;
    }
    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescr() {
        return "Inserts new element in the collection with the provided id.";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) throws BuildObjectException, CommandInterruptedException, WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);
        handler.setOutputModule(outputModule);

        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

        Long id = Utilities.handleUserInputID(args[1]);
        if (id.equals(null)){
            throw new IllegalArgumentException("Id wasn't provided, command wasn't executed.");
        }
        Validator<Long> idValidator = new IdValidator();
        if (idValidator.validate(id)) {
            HumanBeing person = handler.buildObject();
            person.setId(id);
            hashtableManager.addElementToHashtable(person.getId(), person);
        } else {
            new TerminalOutputModule().writeException("Id isn't unique. Can't add an element.");
            throw new CommandInterruptedException("Command wasn't executed.");
        }

        outputModule.writeByDefault("Command executed");
    }
}
