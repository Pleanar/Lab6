package data.validators;

import data.HumanBeing;

/**
 * An implementation of Validator<HumanBeing> for HumanBeing validation.
 */
public class HumanBeingValidator implements Validator<HumanBeing>{

    @Override
    public boolean validate(HumanBeing value) {
        Validator<Long> idValidate = (id) -> id != null && id > 0;

        String weaponType = null;
        if (value.getWeaponType() != null) weaponType = value.getWeaponType().name();
        String mood = null;
        if (value.getMood() != null) mood = value.getMood().name();

        return idValidate.validate(value.getId())
                && new NameValidator().validate(value.getName())
                && new XCoordinateValidator().validate(value.getCoordinates().getX())
                && new YCoordinateValidator().validate(value.getCoordinates().getY())
                && new CreationDateValidator().validate(value.getCreationDate().toString())
                && new RealHeroValidator().validate(value.getRealHero())
                && new HasToothpickValidator().validate(value.isHasToothpick())
                && new ImpactSpeedValidator().validate(String.valueOf(value.getImpactSpeed()))
                && new MinutesOfWaitingValidator().validate(value.getMinutesOfWaiting())
                && new WeaponTypeValidator().validate(weaponType)
                && new MoodValidator().validate(mood)
                && new CarValidator().validate(value.getCar().isCool());
    }
}
