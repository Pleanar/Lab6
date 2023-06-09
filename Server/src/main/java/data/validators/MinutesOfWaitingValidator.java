package data.validators;
/**
 * An implementation of Validator<Float> for HumanBeing's minutesOfWaiting field.
 */
public class MinutesOfWaitingValidator implements Validator<Float>{

    @Override
    public boolean validate(Float value) {
        if (value == null) return true;
        return value <= Float.MAX_VALUE && value >= Float.MIN_VALUE;
    }
}
