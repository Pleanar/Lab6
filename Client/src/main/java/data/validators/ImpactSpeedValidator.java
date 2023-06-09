package data.validators;

/**
 * An implementation of Validator<String> for HumanBeing's impactSpeed field.
 */
public class ImpactSpeedValidator implements Validator<String>{
    @Override
    public boolean validate(String value) {
        if (value == null){
            return false;
        }
        return value.matches("-?\\d+");
    }
}
