package commandManager.commands;

import IOManager.TerminalOutputModule;
import commandManager.CommandStock;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

import java.util.Optional;

public class HelpCommand extends BaseCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescr() {
        return "Shows reference about available commands.";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 0);
        CommandStock manager = new CommandStock();

        if (args.length == 1)
        {
            manager.getInteractiveCommands().forEach((name, command) -> outputModule.writeByDefault(name + " " + command.getArgs() + " --  " + command.getDescr()));
        }
        else
        {
            for (int i = 1; i < args.length; i++)
            {
                var command = manager.getInteractiveCommands().get(args[i]);
                outputModule.writeByDefault(args[i] + " -- " + Optional.ofNullable(command).map(BaseCommand::getDescr).orElse("This command is not found in manager"));
            }
        }
    }
}
