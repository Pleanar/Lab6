package IOManager.Terminal;

import IOManager.InputModule;
import exceptions.StreamInterruptedException;
import main.Utilities;

import java.util.Scanner;

/**
 * An implementation of the InputModule for the Terminal.
 */
public class TerminalInputModule implements InputModule<Scanner> {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Scanner getReader(){
        return scanner;
    }

    @Override
    public String[] readArg() throws StreamInterruptedException {
        String argv[] = null;
        if(Utilities.hasNextLineOrThrow(scanner)){
            String line = scanner.nextLine();
            argv = line.trim().split(" +");
        }
        return argv;
    }

    @Override
    public String readLine() throws StreamInterruptedException{
        String line = null;
        if(Utilities.hasNextLineOrThrow(scanner)){
            line = scanner.nextLine();
        }
        return line;
    }
}
