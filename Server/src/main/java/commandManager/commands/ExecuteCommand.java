package commandManager.commands;

import IOManager.TerminalOutputModule;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import fileManager.ExecutionReader;
import main.Utilities;

public class ExecuteCommand extends BaseCommand{
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "executes a script written in a file (executes commands available in interactive mode).";
    }

    @Override
    public String getArgs() {
        return "file_name (full pathname to the file)";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        Utilities.checkArgumentsOrThrow(args.length, 1);
        String path = args[1];
        ExecutionReader.setOutputModule(outputModule);
        ExecutionReader.readAndExecute(path);
        outputModule.writeByDefault("Command executed");
    }
}
