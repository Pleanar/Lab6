package data.validators;

/**
 * An implementation of Validator<Boolean> for HumanBeing's realHero field.
 */
public class RealHeroValidator implements Validator<Boolean>{
    @Override
    public boolean validate(Boolean value) {
        if (value == null) return false;
        return true;
    }
}
