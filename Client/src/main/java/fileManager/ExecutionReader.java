package fileManager;

import IOManager.OutputModule;
import IOManager.Server.MultiBox;
import IOManager.Terminal.TerminalOutputModule;
import exceptions.StreamInterruptedException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import IOManager.FileInputModule;
import IOManager.InputModule;

/**
 * A class that is used to read a file that contains executable commands.
 */
public class ExecutionReader {
    /**
     * An outputModule that will be used to write.
     */
    private static OutputModule outputModule = new TerminalOutputModule();
    /**
     * A method that allows to read a regular .txt file and execute the commands that exist in a CommandStock.
     * @param path A pathname of the file.
     */
    private static MultiBox<?> multiBox;

    private static TreeSet<String> listOfExecutables = new TreeSet<>();
    public static MultiBox<?> readAndPackage(String path){
        int count = 0;
        boolean flag = false;
        if (!listOfExecutables.add(path)){
            outputModule.writeException("Recursion found!");
            outputModule.writeInfo("The recursion will be averted by not reading the executable file.");
            outputModule.writeInfo("The path to executable file: " + path);
            MultiBox<?> packagedBox;
            if (multiBox.getDepthOfBoxing() > 1){
                packagedBox = new MultiBox<>(multiBox.getMainObject());
                multiBox.getSubBox().getAllObjects().forEach((Object subLine) -> packagedBox.addToBox(subLine));
            } else {
                packagedBox = new MultiBox<>(multiBox.getMainObject());
            }
            multiBox = null;
            return packagedBox;
        }
        try {
            InputModule<Scanner> reader = new FileInputModule(path);
            while (reader.getReader().hasNext()){
                String line = reader.readLine();
                if (!line.isBlank()){
                    String[] argv = line.trim().split(" +");
                    if ((argv.length == 2) && (argv[0].equalsIgnoreCase("execute_script"))){
                        if (flag){
                            addOrCreate(line);
                            count += 1;
                            if (count > 10) {flag = false; count = 0;}
                        } else {
                            MultiBox<?> additionalBox = readAndPackage(argv[1]);
                            additionalBox.getAllObjects().forEach((Object adtObj) -> addOrCreate((String) adtObj));
                        }
                    } else {
                        if ((!argv[0].equalsIgnoreCase("save")) && (!argv[0].equalsIgnoreCase("exit"))) {
                            addOrCreate(line);
                            if (flag){
                                count += 1;
                                if (count > 10) {flag = false; count = 0;}
                            }
                        }
                        if (argv[0].equalsIgnoreCase("insert") || argv[0].equalsIgnoreCase("update")){
                            flag = true;
                        }
                    }
                } else if (flag){
                    addOrCreate(line);
                    count += 1;
                    if (count > 10) {flag = false; count = 0;}
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
        while (count != 0){
            multiBox.addToBox("");
            count -= 1;
        }

        MultiBox<?> packagedBox;
        if (multiBox.getDepthOfBoxing() > 1){
            packagedBox = new MultiBox<>(multiBox.getMainObject());
            multiBox.getSubBox().getAllObjects().forEach((Object subLine) -> packagedBox.addToBox(subLine));
        } else {
            packagedBox = new MultiBox<>(multiBox.getMainObject());
        }
        multiBox = null;
        listOfExecutables.remove(path);
        return packagedBox;
    }
    private static void addOrCreate(String line){
        if (multiBox == null){
            multiBox = new MultiBox(line);
        } else {
            multiBox.addToBox(line);
        }
    }
}