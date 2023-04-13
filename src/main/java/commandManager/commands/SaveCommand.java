package commandManager.commands;


import IOManager.TerminalOutputModule;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import fileManager.XMLWriter;
import main.Utilities;

public class SaveCommand extends BaseCommand{
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Saves current state of collection into the file.";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        XMLWriter.setOutputModule(outputModule);
        XMLWriter.serializeToXML();
        outputModule.writeByDefault("Command executed");
    }
}
