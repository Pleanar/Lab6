package modules;

import IOManager.OutputModule;
import exceptions.BuildObjectException;

/**
 * A class for Object generation.
 * @param <T> Type of the Object.
 */
public interface Module<T> {
    /**
     *A method for Object generation.
     * @return Object of type T.
     * @throws BuildObjectException
     */
    T buildObject() throws BuildObjectException;

    void setOutputModule(OutputModule outputModule);
}
