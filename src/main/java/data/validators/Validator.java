package data.validators;

/**
 * An interface for classes that will validate the fields of entities.
 * @param <T> The type of field that needs to be validated.
 */
@FunctionalInterface
public interface Validator<T> {
    boolean validate(T value);
}
