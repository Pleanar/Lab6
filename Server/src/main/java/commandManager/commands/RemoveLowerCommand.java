package commandManager.commands;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import java.util.Scanner;
import java.util.Set;

public class RemoveLowerCommand extends BaseCommand{
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescr() {
        return "removes all elements from the collection that are lower than provided.";
    }

    @Override
    public String getArgs()
    {
        return "{element}";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException, BuildObjectException, WrongAmountOfArgumentsException, CommandInterruptedException {
        HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();
        HumanBeing person = null;
        try {
            Utilities.checkArgumentsOrThrow(args.length, 1);
            Long key = Utilities.handleUserInputID(args[1]);
            if (key == null){
                throw new IllegalArgumentException("A key should be a Long value.");
            }
            person = hashtableManager.getHumanBeingHashtable().get(key);
            if (person == null) {
                throw new CommandInterruptedException("No such object with the given id in the collection.");
            }
        } catch (WrongAmountOfArgumentsException e) {
            Utilities.checkArgumentsOrThrow(args.length, 0);
            outputModule.writeByDefault("Choose an element from the collection to compare to.");
            outputModule.writeInfo("List of object's ids and contents is provided. To cancel the command write 'cancel' or 'revoke'");
            hashtableManager.getKeys().forEach((key) -> System.out.println(key + ": " + hashtableManager.getHumanBeingHashtable().get(key).toString()));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.next();
                if (line.equals("cancel") || line.equals("revoke")) {
                    throw new CommandInterruptedException("Command was canceled");
                }
                Long key = Utilities.handleUserInputID(line);
                if (key == null) {
                    outputModule.writeException("Given argument wasn't recognized as id.");
                    continue;
                }
                person = hashtableManager.getHumanBeingHashtable().get(key);
                if (person == null) {
                    outputModule.writeException("No such id in the collection.");
                    continue;
                }
                break;
            }
        }
        Set<Long> delete_keys = hashtableManager.getKeys();
        HumanBeing finalPerson = person;
        hashtableManager.getValues().forEach(
                (human) -> {
                    if (human.compareTo(finalPerson) >= 0) {
                        delete_keys.remove(human.getId());
                    }
                });

        delete_keys.forEach((delete_key) -> hashtableManager.getHumanBeingHashtable().remove(delete_key));
        outputModule.writeByDefault("Command executed");
        }
    }
