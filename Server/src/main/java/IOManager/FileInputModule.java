package IOManager;

import exceptions.StreamInterruptedException;
import main.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * An implementation of the InputModule for files.
 */
public class FileInputModule implements InputModule<Scanner>{

    private Scanner scanner = null;

    public FileInputModule(String path) throws FileNotFoundException {
        scanner = new Scanner(new File(path));
    }

    @Override
    public Scanner getReader() {
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
    public String readLine() throws StreamInterruptedException {
        String line = null;
        if(Utilities.hasNextLineOrThrow(scanner)){
            line = scanner.nextLine();
        }
        return line;
    }
}
