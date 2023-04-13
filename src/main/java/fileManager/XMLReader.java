package fileManager;

import IOManager.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.HumanBeing;
import data.validators.HumanBeingValidator;
import data.validators.IdValidator;
import data.validators.Validator;
import exceptions.StreamInterruptedException;
import hashtables.HashtableManager;
import hashtables.HumanBeingIds;
import hashtables.HumanBeingManager;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class used to read an XML file that contains Objects needed to be loaded.
 */
public class XMLReader{
    /**
     * An outputModule that will be used to write.
     */
    private static OutputModule outputModule = new TerminalOutputModule();

    /**
     * A method that allows to change the outputModule of the class.
     * @param outputModule is an outputModule object that will be used by this class.
     */
    public static void setOutputModule(OutputModule outputModule) {
        XMLReader.outputModule = outputModule;
    }

    /**
     * A method that allows to read the XML file and add the elements to the HashTable.
     * @param env An environmental value that contains the pathname to the file.
     * @param inputModule An inputModule that will be used to read the file.
     */
    public static void readFromXML(String env, InputModule inputModule){
        try {
            String envPath = System.getenv(env);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            InputModule<Scanner> fileInputModule = new FileInputModule(envPath);
            InputModule<Scanner> terminalInputModule = inputModule;
            String stringXML = "";
            while (fileInputModule.getReader().hasNext()){
                String line = fileInputModule.readLine();
                if (!line.isEmpty()){
                    stringXML = stringXML + line;
                }
            }

            ArrayList<Object> objects = xmlMapper.readerForListOf(HumanBeing.class).readValue(stringXML);

            HashtableManager<Long, HumanBeing> humanBeingManager = HumanBeingManager.getInstance();
            objects.forEach((object) -> {if (object.getClass().equals(HumanBeing.class)){
                HumanBeing person = (HumanBeing) object;

                Validator<HumanBeing> humanBeingValidator = new HumanBeingValidator();

                if (humanBeingValidator.validate(person)){

                    Validator<Long> idValidator = new IdValidator();
                    if (idValidator.validate(person.getId())) {
                    humanBeingManager.addElementToHashtable(person.getId(), person);
                    } else {
                        outputModule.writeException("Id of a person already exists. Do you wish to replace the id?[y/n] If \"n\" option is chosen, then person won't be added.");
                        while (terminalInputModule.getReader().hasNext()) {
                            String line = terminalInputModule.getReader().next();
                            if (line.equals("y") || line.equals("yes")) {
                                Long replaceId = HumanBeingIds.generateID();
                                person.setId(replaceId);
                                outputModule.writeInfo("Id is set to " + person.getId());
                                humanBeingManager.addElementToHashtable(person.getId(), person);
                                break;
                            }
                            if (line.equals("n") || line.equals("no")) {
                                outputModule.writeByDefault("Person won't be added");
                                break;
                            }
                        }
                    }
                } else {
                outputModule.writeException("Person's fields don't meet the requirements.");
                outputModule.writeInfo("Object is skipped.");
                }
                }
            });
        } catch (SecurityException e){
            outputModule.writeException("Not enough rights to access the file");
            outputModule.writeInfo("Program will be working without the file.");
        } catch (JsonProcessingException e) {
            outputModule.writeException("An error has occurred while parsing. Check your file and fix it manually.");
            outputModule.writeInfo("Program will be working without the file.");
        } catch (FileNotFoundException e){
            outputModule.writeException("File wasn't found, please check your folder.");
            outputModule.writeInfo("Program will be working without the file.");
        } catch (NullPointerException e){
            outputModule.writeException("No such environmental variable value was found. Please check your variable.");
            outputModule.writeInfo("Program will be working without the file.");
        } catch (StreamInterruptedException e){
            outputModule.writeError(e.getMessage());
        }
        outputModule.writeInfo("Loading from the file is completed.");
    }
}
