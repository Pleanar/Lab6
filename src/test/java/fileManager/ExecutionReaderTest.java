package fileManager;

import data.HumanBeing;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionReaderTest {

    static HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();

    @BeforeAll
    static void setup(){
        assertEquals(hashtableManager.getValues().size(), 0);
    }

    @Test
    void readAndExecute() {
        ExecutionReader.readAndExecute("C:\\Users\\Public\\Documents\\lab5\\TestExecution.txt");
        assertEquals(hashtableManager.getValues().size(), 1);
        HumanBeing person = new HumanBeing(3L, "lulz", 100L, 200F, true, false, 100001, 10.1F, "AXE", "APATHY", false);
        person.setCreationDate(LocalDate.parse("2021-12-31"));
        assert (hashtableManager.getValues().contains(person));
    }
}