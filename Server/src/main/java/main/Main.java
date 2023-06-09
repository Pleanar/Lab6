package main;

import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalInputModule;
import IOManager.TerminalOutputModule;
import commandManager.CommandHandler;
import exceptions.StreamInterruptedException;
import fileManager.XMLReader;
import fileManager.XMLWriter;

/**
 * Program entry point class. Contains main() method.
 * This program is managing collection of type HashTable
 * @version 1.0
 * @author pleanar
 */
public class Main {
    /**
     * Environmental key to XML file that will be loaded
     */
    public static final String ENV_KEY = "lab5";
    /**
     * A inputModule that will be used by user for input.
     */
    public static InputModule inputModule = new TerminalInputModule();
    /**
     * A outputModule that will be used to write back to user.
     */
    public static OutputModule outputModule = new TerminalOutputModule();

    /**
     * Entry-point of the program.
     * @param args Command-line arguments
     */

    public static void main(String[] args) {
        XMLReader.setOutputModule(outputModule);
        CommandHandler.setOutputModule(outputModule);
        Utilities.setOutputModule(outputModule);
        XMLWriter.setInputModule(inputModule);

        XMLReader.readFromXML(ENV_KEY, inputModule);
        while (true){
            try {
                CommandHandler.invokeCommand(inputModule.readArg());
            } catch (StreamInterruptedException e){
                outputModule.writeError(e.getMessage());
                System.exit(-1);
            }
        }
    }
}