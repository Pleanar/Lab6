package IOManager;

/**
 * An interface for classes that are used for the output of String values.
 */
public interface OutputModule{
    /**
     * Writes an INFO line to the output.
     * @param line String.
     */
    public void writeInfo(String line);
    /**
     * Writes an EXCEPTION line to the output.
     * @param line String.
     */
    public void writeException(String line);
    /**
     * Writes an ERROR line to the output.
     * @param line String.
     */
    public void writeError(String line);
    /**
     * Writes a regular line to the output.
     * @param line String.
     */
    public void writeByDefault(String line);
}
