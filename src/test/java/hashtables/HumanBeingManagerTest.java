package hashtables;

import commandManager.commands.ClearCommand;
import data.HumanBeing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class HumanBeingManagerTest {

    static HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

    @Test
    void setHumanBeingHashtable() {
        Hashtable<Long, HumanBeing>  hashtable = new Hashtable<>();
        HumanBeing person = new HumanBeing(3L, "lulz", 100L, 200F, true, false, 100001, 10.1F, "AXE", "APATHY", false);
        person.setCreationDate(LocalDate.parse("2021-12-31"));

        hashtable.put(1L, new HumanBeing());
        hashtable.put(0L, person);
        hashtable.put(3L, person);

        hashtableManager.setHumanBeingHashtable(hashtable);
        assert (hashtableManager.getValues().contains(person));
        assert (hashtableManager.getValues().size() == 1);
    }
}