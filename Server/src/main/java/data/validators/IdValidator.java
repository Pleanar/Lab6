package data.validators;

import hashtables.HumanBeingManager;
import java.util.Set;
import java.util.TreeSet;

/**
 * An implementation of Validator<Long> for HumanBeing's id field that considers the current state HumanBeingManager.
 */
public class IdValidator implements Validator<Long>{
    private TreeSet<Long> ids;

    public IdValidator()
    {
        ids = new TreeSet<>();

        Set<Long> keys = HumanBeingManager.getInstance().getKeys();

        keys.forEach((value) -> ids.add(value));
    }

    @Override
    public boolean validate(Long value) {
        if (value == null) return false;
        if (value <= 0) return false;
        return ids.add(value);
    }
}
