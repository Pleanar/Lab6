package hashtables.modules;

import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import data.Car;
import data.HumanBeing;
import data.Mood;
import data.WeaponType;
import hashtables.HumanBeingIds;
import data.validators.*;
import exceptions.*;
import hashtables.HumanBeingManager;
import main.Utilities;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * An implementation of the Module<HumanBeing> that's used for HumanBeing object creation with user's input.
 */
public class HumanBeingModule implements Module<HumanBeing> {
    private static OutputModule outputModule = new TerminalOutputModule();

    public void setOutputModule(OutputModule outputModule){
        HumanBeingModule.outputModule = outputModule;
    }

    @Override
    public HumanBeing buildObject() throws BuildObjectException {
        try {
            outputModule.writeByDefault("Generating object...");
            HumanBeing result = new HumanBeing();
            outputModule.writeByDefault("Welcome to master of HumanBeing object creation!");
            outputModule.writeByDefault("Follow the instructions to setup your object.");

            Scanner scanner = new Scanner(System.in);

            // id
            result.setId(HumanBeingIds.generateID());

            // name
            Validator<String> nameValidator = new NameValidator();
            String name = null;
            do {
                outputModule.writeByDefault("Enter the value of name (Type: String)");
                if (Utilities.hasNextLineOrThrow(scanner)) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        name = line;
                }
                if (!nameValidator.validate(name)) {
                    outputModule.writeException("Value violates restrictions for field! Try again.");
                    outputModule.writeInfo("Restrictions: Should be not null and not empty.");
                }
            } while (!nameValidator.validate(name));
            result.setName(name);

            // coordinates
            outputModule.writeByDefault("Starting coordinates field setup...");
            CoordinatesModule coordinatesModule = new CoordinatesModule();
            result.setCoordinates(coordinatesModule.buildObject());

            // realHero
            outputModule.writeByDefault("Is HumanBeing a real hero? [y/n]. This field cannot be skipped.");
            String answer = scanner.next();
            while (true) {
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("yes")) {
                    result.setRealHero(true);
                    outputModule.writeInfo("Field is set to \"true\"");
                    break;
                }
                if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("false") || answer.equalsIgnoreCase("no")){
                    result.setRealHero(false);
                    outputModule.writeInfo("Field is set to \"false\"");
                    break;}
                outputModule.writeException("Value violates restrictions for field! Try again.");
                outputModule.writeInfo("Restrictions: Should be [y/n].");
                answer = scanner.next();
            }

            // hasToothpick
            outputModule.writeByDefault("Does HumanBeing have a Toothpick? [y/n]. This field cannot be skipped.");
            answer = scanner.next();
            while (true) {
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("yes")) {
                    result.setRealHero(true);
                    outputModule.writeInfo("Field is set to \"true\"");
                    break;
                }
                if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("false") || answer.equalsIgnoreCase("no")) {
                    result.setRealHero(false);
                    outputModule.writeInfo("Field is set to \"false\"");
                    break;
                }
                outputModule.writeException("Given answer wasn't recognized.");
                outputModule.writeInfo("Restrictions: answer should be [y/n].");
                answer = scanner.next();
            }

            // impactSpeed
            outputModule.writeByDefault("Enter the value of impactSpeed (Type: long). This field cannot be skipped.");
            answer = scanner.next();
            while (true) {
                if (answer.matches("-?\\d+")){
                    result.setImpactSpeed(Long.parseLong(answer));
                    break;
                }
                outputModule.writeException("Given answer wasn't recognized.");
                outputModule.writeInfo("Restrictions: type a long value");
                answer = scanner.next();
            }

            // minutesOfWaiting
            outputModule.writeByDefault("Enter the value of minutes of waiting (Type: Float). This field can be skipped.");
            answer = scanner.next();
            while (true){
                if (!Utilities.isNotNumeric(answer)){
                    result.setMinutesOfWaiting(Float.parseFloat(answer));
                    break;
                }
                if (answer.equalsIgnoreCase("skip") || answer.equalsIgnoreCase("")){
                    result.setMinutesOfWaiting(null);
                    break;
                }
                outputModule.writeException("Value violates restrictions for field! Try again.");
                outputModule.writeInfo("Restrictions: Should be Float or write ['skip'/''] to skip the field.");
                answer = scanner.next();
            }

            // weaponType
            outputModule.writeByDefault("Enter the value of weaponType. This field can be skipped.");
            Arrays.stream(WeaponType.values()).toList().forEach((weapon) -> System.out.print(weapon.name() + "\n"));
            outputModule.writeInfo("Possible answers are above.");
            answer = scanner.next();

            while (true){
                if (answer.equalsIgnoreCase("skip") || answer.equalsIgnoreCase("")){
                    result.setWeaponType(null);
                    break;
                }
                WeaponType weapon = WeaponType.valueOf(answer.toUpperCase());
                if (weapon != null){
                    result.setWeaponType(weapon);
                    break;
                }
                outputModule.writeException("Given answer wasn't recognized. Try again.");
                answer = scanner.next();
            }

            // mood
            outputModule.writeByDefault("Enter the value of mood. This field can be skipped.");
            Arrays.stream(Mood.values()).toList().forEach((mood) -> System.out.print(mood.name() + "\n"));
            outputModule.writeInfo("Possible answers are above.");
            answer = scanner.next();

            while (true){
                if (answer.equalsIgnoreCase("skip") || answer.equalsIgnoreCase("")){
                    result.setMood(null);
                    break;
                }
                Mood mood = Mood.valueOf(answer.toUpperCase());
                if (mood != null){
                    result.setMood(mood);
                    break;
                }
                outputModule.writeException("Given answer wasn't recognized. Try again.");
                answer = scanner.next();
            }

            // car
            outputModule.writeByDefault("Enter the value for creating a Car (Value type is Boolean). This field cannot be skipped.");
            outputModule.writeByDefault("Is the Car cool?[y/n]");
            answer = scanner.next();
            while (true){
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("yes")) {
                    result.setCar(new Car(true));
                    outputModule.writeInfo("Field is set to \"true\"");
                    break;
                }
                if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("false") || answer.equalsIgnoreCase("no")) {
                    result.setCar(new Car(false));
                    outputModule.writeInfo("Field is set to \"false\"");
                    break;
                }
                outputModule.writeException("Given answer wasn't recognized.");
                outputModule.writeInfo("Restrictions: answer should be [y/n].");
                answer = scanner.next();
            }

            // creationDate
            outputModule.writeByDefault("Do you wish to set creation time?[y/n] Field will be autogenerated, if 'n' is chosen.");
            answer = scanner.next();
            while (true) {
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("yes")){
                    String date = "";
                    outputModule.writeByDefault("Write the year in a number.");
                    answer = scanner.next();
                    while (true){
                        if(answer.matches("-?\\d+") && (Integer.valueOf(answer) > -999999999) && (Integer.valueOf(answer) < 999999999)){
                            date += answer;
                            break;
                        }
                        outputModule.writeException("The year was written incorrectly.");
                        outputModule.writeInfo("The date is counted from " + LocalDate.MIN +" to " + LocalDate.MAX.toString() + " as a number.");
                        answer = scanner.next();
                    }
                    String yearDate = "";
                    for (int i = 0; i < date.length(); i++){
                        yearDate += "y";
                    }
                    DateTimeFormatter dTF = DateTimeFormatter.ofPattern(yearDate + "-MM-dd");
                    outputModule.writeByDefault("Write the month in a number.");
                    answer = scanner.next();
                    while (true){
                        if(answer.matches("\\d+") && (Integer.valueOf(answer) <= 12) && (Integer.valueOf(answer) >= 1)){
                            date += ("-" + answer + "-");
                            break;
                        }
                        outputModule.writeException("Month was written incorrectly");
                        outputModule.writeInfo("The month should be written as a number between 1 and 12");
                        answer = scanner.next();
                    }
                    outputModule.writeByDefault("Write the day in a number");
                    answer = scanner.next();
                    while (true){
                        try{
                            dTF.parse(date + answer);
                            LocalDate creationDate = LocalDate.parse(date + answer, dTF);
                            outputModule.writeInfo("Object created at " + creationDate);
                            result.setCreationDate(creationDate);
                            break;
                        } catch (DateTimeParseException e){
                            outputModule.writeException(e.getParsedString());
                            outputModule.writeInfo(date + answer);
                            outputModule.writeException("The date wasn't written properly.");
                        }
                        answer = scanner.next();
                    }
                    break;
                }
                if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("false") || answer.equalsIgnoreCase("no")){
                    LocalDate creationDate = LocalDate.now();
                    outputModule.writeInfo("Object created at " + creationDate);
                    result.setCreationDate(creationDate);
                    break;
                }
            }

            outputModule.writeByDefault("Object setup completed! Sending result...");

            return result;

        } catch (StreamInterruptedException e) {
            throw new BuildObjectException("During a build process an error has occurred: " + e.getMessage());
        }
    }
}