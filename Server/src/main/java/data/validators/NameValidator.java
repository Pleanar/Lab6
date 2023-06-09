package data.validators;

/**
 * An implementation of Validator<String> for HumanBeing's name field.
 */
public class NameValidator implements Validator<String>{

    @Override
    public boolean validate(String value) {
        if (value == null || value.isEmpty()) return false;
        return true;
    }
}
