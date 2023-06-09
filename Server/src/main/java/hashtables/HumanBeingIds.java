package hashtables;

import data.validators.IdValidator;
import data.validators.Validator;

/**
 * A utility class for the ID generation for current HashTable.
 */
public class HumanBeingIds {

    private static Long id = 1L;

    /**
     * A method that generates the ID based on the currents state of the HashTable.
     * @return type Long value that can be used as ID.
     */

    public static Long generateID(){

        Validator<Long> idValidator = new IdValidator();

        while (!idValidator.validate(id)){
            id += 1;
        }

        return id;
    }
}
