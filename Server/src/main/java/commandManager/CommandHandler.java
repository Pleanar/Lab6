package commandManager;

import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import commandManager.commands.BaseCommand;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import hashtables.HumanBeingManager;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for commands execution.
 */
public class CommandHandler {
    /**
     * A variable that contains the commandStock class that contains all commands that are handled by user.
     */
    private static CommandStock commandStock = new CommandStock();
    /**
     * A variable that contains the commandStock class that contains all commands that are not handled by user.
     */
    private static CommandStock nonInteractiveCommandStock = null;
    /**
     * A variable that is used to contain 12 latest used commands.
     */
    private static Deque<String> history = new ArrayDeque<>(12);
    /**
     * An outputModule that will be used to write.
     */
    private static OutputModule outputModule = new TerminalOutputModule();

    /**
     * A method used for command execution that needs user's input.
     * @param argv String[] that contains the command and it's arguments, if needed.
     */
    public static void invokeCommand(String[] argv){
        if (argv.length > 0){
            String commandName = argv[0];
            if (commandStock.getInteractiveCommands().containsKey(commandName)){
                try {
                    commandStock.getInteractiveCommands().get(commandName).execute(argv);
                    if (history.size() < 12) history.addLast(argv[0]);
                    else {
                        history.removeFirst();
                        history.addLast(argv[0]);
                    }
                } catch (IllegalArgumentException | CommandInterruptedException | BuildObjectException |
                         WrongAmountOfArgumentsException e) {
                    System.out.println(e);
                }
            } else {
                outputModule.writeException("'" + argv[0] + "' - No such command exist");
                outputModule.writeInfo("Write 'help' to see all of the commands available.");
            }
        } else {
            outputModule.writeByDefault("Write a command. Write 'help' to get a list of commands.");
        }
    }

    /**
     * A method used for command execution that don't need user's input.
     * @param argv String[] that contains the command and it's arguments, if needed.
     */
    public static void invokeNonInteractiveCommand(String[] argv, InputModule reader){
        nonInteractiveCommandStock = new CommandStock(reader);
        String commandName = argv[0];
        if (nonInteractiveCommandStock.getNonInteractiveCommands().containsKey(commandName)){
            try {
                nonInteractiveCommandStock.getNonInteractiveCommands().get(commandName).execute(argv);
                if (history.size() < 12) history.addLast(argv[0]);
                else {
                    history.removeFirst();
                    history.addLast(argv[0]);
                }
            } catch (IllegalArgumentException | CommandInterruptedException | BuildObjectException |
                     WrongAmountOfArgumentsException e) {
                System.out.println(e);
            }
        } else {
            invokeCommand(argv);
        }
    }

    /**
     * A method that returns the reference to history of 12 last used commands.
     * @return Deque<String> that contains the names of 12 last used commands.
     */
    public static Deque<String> getHistory(){
        return history;
    }

    public static void setOutputModule(OutputModule outputModule) {
        CommandHandler.outputModule = outputModule;
        BaseCommand.setOutputModule(outputModule);
        commandStock = new CommandStock();
        HumanBeingManager.setOutputModule(outputModule);
    }
}
