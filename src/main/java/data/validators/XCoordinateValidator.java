package data.validators;

/**
 * An implementation of Validator<Long> for Coordinates X field.
 */
public class XCoordinateValidator implements Validator<Long>{
    @Override
    public boolean validate(Long value) {
        if (value == null) return false;
        return value <= Long.MAX_VALUE && value >= Long.MIN_VALUE;
    }
}
