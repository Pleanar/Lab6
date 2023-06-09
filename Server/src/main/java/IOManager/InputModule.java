package IOManager;

import exceptions.StreamInterruptedException;

/**
 * An interface for classes that are used for the input.
 * @param <T> type of the reading class.
 */

public interface InputModule<T> {
    /**
     * A method that returns the class that's used for reading the input.
     * @return reference to the reading class that is used.
     */
    public T getReader();

    /**
     * A method that allows to read the arguments from the line.
     * @return String[] that contains arguments from one line.
     * @throws StreamInterruptedException
     */
    public String[] readArg() throws StreamInterruptedException;

    /**
     * A method that allows to read whole line.
     * @return String that contains whole line.
     * @throws StreamInterruptedException
     */
    public String readLine() throws StreamInterruptedException;
}
