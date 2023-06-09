package data.validators;

import data.Mood;

import java.util.Arrays;

/**
 * An implementation of Validator<String> for HumanBeing's mood field.
 */
public class MoodValidator implements Validator<String>{
    @Override
    public boolean validate(String value) {
        if (value == null) return true;
        try {
            Mood.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
