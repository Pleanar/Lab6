package commandManager.commands;

import commandManager.CommandHandler;
import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearCommandTest {
    static HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

    @Test
    void getName() {
        assertEquals(new ClearCommand().getName(), "clear");

    }

    @Test
    void execute() {
        hashtableManager.addElementToHashtable(1L, new HumanBeing());
        String line = "    clear    ";
        String[] argv = line.trim().split(" +");
        CommandHandler.invokeCommand(argv);
        assertEquals(hashtableManager.getValues().size(), 0);
    }
}