package data.validators;

/**
 * An implementation of Validator<Boolean> for HumanBeing's hasToothpick field.
 */
public class HasToothpickValidator implements Validator<Boolean>{
    @Override
    public boolean validate(Boolean value) {
        if (value == null){
            return false;
        }
        return true;
    }
}
