package data.validators;

import java.time.LocalDate;

/**
 * An implementation of Validator<String> for HumanBeing's creationDate field.
 */
public class CreationDateValidator implements Validator<String>{

    @Override
    public boolean validate(String value) {
        if (value == null) return false;
        try {
            LocalDate date = LocalDate.parse(value);
        }catch(Exception DateTimeParseException){
            return false;
        }
        return true;
    }
}
