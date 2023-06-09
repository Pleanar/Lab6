package data.validators;

import data.WeaponType;

/**
 * An implementation of Validator<String> for HumanBeing's weaponType field.
 */
public class WeaponTypeValidator implements Validator<String>{

    @Override
    public boolean validate(String value) {
        if (value == null) return true;
        try {
            WeaponType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
        return false;
        }
    }
}
