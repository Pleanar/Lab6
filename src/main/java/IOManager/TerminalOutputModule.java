package IOManager;

/**
 * An implementation of the OutputModule for the System.out.
 */
public class TerminalOutputModule implements OutputModule{
    @Override
    public void writeInfo(String line) {
        System.out.println("Info: " + line);
    }

    @Override
    public void writeException(String line) {
        System.out.println("Exception: " + line);
    }

    @Override
    public void writeError(String line){
        System.out.println("Error: " + line);
    }

    @Override
    public void writeByDefault(String line){
        System.out.println(line);
    }
}
