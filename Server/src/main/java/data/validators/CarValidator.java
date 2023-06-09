package data.validators;

/**
 * An implementation of Validator<Boolean> for Car's cool field.
 */
public class CarValidator implements Validator<Boolean>{

    @Override
    public boolean validate(Boolean value) {
        if (value == null) return false;
        return true;
    }
}
