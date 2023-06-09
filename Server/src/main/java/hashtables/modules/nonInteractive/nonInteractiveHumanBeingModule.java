package hashtables.modules.nonInteractive;

import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.*;
import data.validators.HumanBeingValidator;
import data.validators.Validator;
import exceptions.BuildObjectException;
import exceptions.StreamInterruptedException;
import hashtables.HumanBeingIds;
import hashtables.modules.Module;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An implementation of the Module<HumanBeing> that allows to generate a HumanBeing object without user's input.
 */
public class nonInteractiveHumanBeingModule implements Module<HumanBeing> {
    private InputModule reader;

    private static OutputModule outputModule = new TerminalOutputModule();

    public nonInteractiveHumanBeingModule(InputModule reader){
        this.reader = reader;
    }

    @Override
    public HumanBeing buildObject() throws BuildObjectException{
        Integer valuesToRead = 11;

        ArrayList<String> values = new ArrayList<>(valuesToRead);

        try {
            for (int i = 0; i < valuesToRead; i++) {
                String line = reader.readLine();
                if (!line.isEmpty())
                    values.add(line.trim());
                else {
                    values.add(null);
                }
            }
        } catch (StreamInterruptedException e){
            outputModule.writeException(e.getMessage());
        }

        HumanBeing result;
        try {
            Long id = HumanBeingIds.generateID();
            String name = values.get(0);
            Coordinates coordinates = new Coordinates(Long.valueOf(values.get(1)), Float.valueOf(values.get(2)));
            LocalDate creationDate = LocalDate.parse(values.get(3));
            Boolean realHero;
            switch (values.get(4).toLowerCase()){
                case "true" -> realHero = true;
                case "false" -> realHero = false;
                default -> realHero = null;
            }
            Boolean hasToothpick;
            switch (values.get(5).toLowerCase()){
                case "true" -> hasToothpick = true;
                case "false" -> hasToothpick = false;
                default -> hasToothpick = null;
            }
            Long impactSpeed = Long.valueOf(values.get(6));
            Float minutesOfWaiting = null;
            if (values.get(7) != null) minutesOfWaiting = Float.valueOf(values.get(7));
            WeaponType weaponType = null;
            if (values.get(8) != null) weaponType = WeaponType.valueOf(values.get(8).toUpperCase());
            Mood mood = null;
            if (values.get(9) != null) mood = Mood.valueOf(values.get(9).toUpperCase());
            Car car = new Car();
            switch (values.get(10).toLowerCase()){
                case "true" -> car.setCool(true);
                case "false" -> car.setCool(false);
                default -> car.setCool(null);
            }

            result = new HumanBeing();
            result.setId(id);
            result.setName(name);
            result.setCoordinates(coordinates);
            result.setCreationDate(creationDate);
            result.setRealHero(realHero);
            result.setHasToothpick(hasToothpick);
            result.setImpactSpeed(impactSpeed);
            result.setMinutesOfWaiting(minutesOfWaiting);
            result.setWeaponType(weaponType);
            result.setMood(mood);
            result.setCar(car);

            Validator<HumanBeing> validator = new HumanBeingValidator();
            if (!validator.validate(result)) throw new BuildObjectException("Object wasn't build due to field restrictions.");
        } catch (IllegalArgumentException e){
            throw new BuildObjectException("Some of the values are incorrect.");
        } catch (IndexOutOfBoundsException e){
            throw new BuildObjectException("No arguments left for object creation.");
        } catch (NullPointerException e){
            throw new BuildObjectException("Cannot set argument to null.");
        }
        return result;
    }

    public void setOutputModule(OutputModule outputModule) {
        nonInteractiveHumanBeingModule.outputModule = outputModule;
    }
}
