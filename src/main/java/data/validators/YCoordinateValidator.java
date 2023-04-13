package data.validators;

/**
 * An implementation of Validator<Float> for Coordinates Y field.
 */
public class YCoordinateValidator implements Validator<Float>{
    @Override
    public boolean validate(Float value) {
        if (value == null) return false;
        return value <= Float.MAX_VALUE && value >= Float.MIN_VALUE;
    }
}
