package commandManager;

import commandManager.commands.*;
import java.util.LinkedHashMap;

/**
 * A class that contains all of available commands with their names and references to them.
 */
public class CommandStock {

    private LinkedHashMap<String, BaseCommand> specialCommands;

    /**
     * Setup for all the interactive commands.
     */
    public CommandStock()
    {
        specialCommands = new LinkedHashMap<>();

        specialCommands.put("insert", new InsertCommand());
        specialCommands.put("update", new UpdateCommand());
        specialCommands.put("execute_script", new ExecuteCommand());
        specialCommands.put("exit", new ExitCommand());
        specialCommands.put("remove_lower", new RemoveLowerCommand());
    }

    public LinkedHashMap<String, BaseCommand> getSpecialCommands() {
        return specialCommands;
    }
}
