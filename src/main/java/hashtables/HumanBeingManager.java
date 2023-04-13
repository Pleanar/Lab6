package hashtables;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.HumanBeing;

import java.time.LocalDateTime;
import data.validators.*;

import java.util.*;

/**
 * Current implementation of the HashtableManager that has a Long values as keys and HumanBeing objects as values.
 */
public class HumanBeingManager implements HashtableManager<Long, HumanBeing>{

    private static OutputModule outputModule = new TerminalOutputModule();
    private Long key = 0L;
    private HumanBeing value = new HumanBeing();

    private static HumanBeingManager singltonHumanBeingManager;

    private Hashtable<Long, HumanBeing> HumanBeingHashtable;
    final LocalDateTime initDate;

    private HumanBeingManager(){
        HumanBeingHashtable = new Hashtable<Long, HumanBeing>();
        initDate = LocalDateTime.now();
    }

    public static HumanBeingManager getInstance() {
        if (singltonHumanBeingManager == null)
            singltonHumanBeingManager = new HumanBeingManager();
        return singltonHumanBeingManager;
    }

    public static void setOutputModule(OutputModule outputModule){
        HumanBeingManager.outputModule = outputModule;
    }

    public Hashtable<Long, HumanBeing> getHumanBeingHashtable() {
        return this.HumanBeingHashtable;
    }

    public void setHumanBeingHashtable(Hashtable<Long, HumanBeing> humanBeingHashtable) {
        HumanBeingHashtable = humanBeingHashtable;
        validateElements();
    }

    public void addElementToHashtable(Long id, HumanBeing person){
        getHumanBeingHashtable().put(id, person);
    }

    @Override
    public LocalDateTime getInitDate() {
        return initDate;
    }

    @Override
    public Set<Long> getKeys() {
        return new HashSet<>(getInstance().getHumanBeingHashtable().keySet());
    }

    @Override
    public String getKeyType() {
        return key.getClass().getSimpleName();
    }

    @Override
    public Set<HumanBeing> getValues() {
        return new HashSet<>(getInstance().getHumanBeingHashtable().values());
    }

    @Override
    public String getValueType() {
        return value.getClass().getSimpleName();
    }

    @Override
    public void validateElements() {

        Validator<HumanBeing> validator = new HumanBeingValidator();

        for (Iterator<Long> it = getKeys().iterator(); it.hasNext(); ) {
            Long keyToValid = it.next();
            HumanBeing toValid = getHumanBeingHashtable().get(keyToValid);

            if (!validator.validate(toValid))
            {
                getHumanBeingHashtable().remove(keyToValid);
                outputModule.writeException("Element removed from collection: " + toValid.toString() + ". This element violates the restriction of some fields. Check your collection and fix it manually.");
            }
        }
    }
}
