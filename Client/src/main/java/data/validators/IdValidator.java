package data.validators;

import main.Utilities;

/**
 * An implementation of Validator<Long> for HumanBeing's id field.
 */
public class IdValidator implements Validator<Long>{

    @Override
    public boolean validate(Long value) {
        if (value >= 0) return true;
        else return false;
    }
}
