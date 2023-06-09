package fileManager;

import IOManager.FileInputModule;
import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalOutputModule;
import commandManager.CommandHandler;
import exceptions.StreamInterruptedException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that is used to read a file that contains executable commands.
 */
public class ExecutionReader {
    /**
     * An outputModule that will be used to write.
     */
    private static OutputModule outputModule = new TerminalOutputModule();

    /**
     * A method that allows to change the outputModule of the class.
     * @param outputModule is an outputModule object that will be used by this class.
     */
    public static void setOutputModule(OutputModule outputModule) {
        ExecutionReader.outputModule = outputModule;
    }

    /**
     * A method that allows to read a regular .txt file and execute the commands that exist in a CommandStock.
     * @param path A pathname of the file.
     */
    public static void readAndExecute(String path){
        try {
            InputModule<Scanner> reader = new FileInputModule(path);
            while (reader.getReader().hasNext()){
                String line = reader.readLine();
                if (!line.isBlank()){
                    String[] argv = line.trim().split(" +");
                    CommandHandler.invokeNonInteractiveCommand(argv, reader);
                }
            }
        } catch (NullPointerException e){
            outputModule.writeException("Pathname is null.");
        } catch (FileNotFoundException e){
            outputModule.writeException("No file found at the given pathname.");
        } catch (SecurityException e){
            outputModule.writeException("Not enough rights to access a file.");
        } catch (StreamInterruptedException e) {
            outputModule.writeError(e.getMessage());
        }
    }
}
