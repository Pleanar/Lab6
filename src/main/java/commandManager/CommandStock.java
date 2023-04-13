package commandManager;

import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import commandManager.commands.*;
import hashtables.modules.nonInteractive.nonInteractiveHumanBeingModule;
import java.util.LinkedHashMap;

/**
 * A class that contains all of available commands with their names and references to them.
 * Used by CommandHandler to execute commands.
 * @see CommandHandler
 */
public class CommandStock {

    private LinkedHashMap<String, BaseCommand> interactiveCommands;
    private LinkedHashMap<String, BaseCommand> nonInteractiveCommands;

    /**
     * Setup for all the interactive commands.
     */
    public CommandStock()
    {
        interactiveCommands = new LinkedHashMap<>();

        interactiveCommands.put("help", new HelpCommand());
        interactiveCommands.put("info", new InfoCommand());
        interactiveCommands.put("show", new ShowCommand());
        interactiveCommands.put("insert", new InsertCommand());
        interactiveCommands.put("update", new UpdateCommand());
        interactiveCommands.put("remove_key", new RemoveKeyCommand());
        interactiveCommands.put("clear", new ClearCommand());
        interactiveCommands.put("save", new SaveCommand());
        interactiveCommands.put("execute_script", new ExecuteCommand());
        interactiveCommands.put("exit", new ExitCommand());
        interactiveCommands.put("remove_lower", new RemoveLowerCommand());
        interactiveCommands.put("history", new HistoryCommand());
        interactiveCommands.put("remove_lower_key", new RemoveLowerKeyCommand());
        interactiveCommands.put("remove_all_by_minutes_of_waiting", new RemoveAllByMinutesOfWaitingCommand());
        interactiveCommands.put("average_of_minutes_of_waiting", new AverageOfMinutesOfWaitingCommand());
        interactiveCommands.put("filter_contains_name", new FilterContainsNameCommand());
    }

    /**
     * Setup for all non-interactive commands.
     * @param reader A reader that's used by the commands instead of user's input.
     */
    public CommandStock(InputModule reader){
        nonInteractiveCommands = new LinkedHashMap<>();

        nonInteractiveCommands.put("insert", new InsertCommand(new nonInteractiveHumanBeingModule(reader)));
        nonInteractiveCommands.put("update", new UpdateCommand(new nonInteractiveHumanBeingModule(reader)));
    }

    public LinkedHashMap<String, BaseCommand> getInteractiveCommands() {
        return interactiveCommands;
    }

    public LinkedHashMap<String, BaseCommand> getNonInteractiveCommands() {
        return  nonInteractiveCommands;
    }
}
