package fileManager;

import IOManager.InputModule;
import IOManager.OutputModule;
import IOManager.TerminalInputModule;
import IOManager.TerminalOutputModule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.HumanBeing;
import exceptions.StreamInterruptedException;
import hashtables.HashtableManager;
import hashtables.HumanBeingManager;
import main.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that allows to write the values from the HashTable into a XML file.
 */

public class XMLWriter{
    /**
     * An outputModule that will be used to write back to user.
     */
    private static OutputModule outputModule = new TerminalOutputModule();
    /**
     * A inputModule that will be used by user for input.
     */
    private static InputModule inputModule = new TerminalInputModule();

    /**
     * A method that allows to change the outputModule of the class.
     * @param outputModule is an outputModule object that will be used by this class.
     */
    public static void setOutputModule(OutputModule outputModule) {
        XMLWriter.outputModule = outputModule;
    }

    /**
     * A method that allows to change the inputModule of the class.
     * @param inputModule is an inputModule object that will be used by this class.
     */
    public static void setInputModule(InputModule inputModule) {
        XMLWriter.inputModule = inputModule;
    }
    /**
     * A method that allows to serialize Objects to the XML format and write it as an ArrayList of Objects.
     */
    public static void serializeToXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            File xmlOutput = new File("/home/studs/s367824/Programming/lab5/OutSerializedHumanBeing.xml");
            PrintWriter fileWriter = new PrintWriter(xmlOutput);

            HashtableManager<Long, HumanBeing> hashtableManager = HumanBeingManager.getInstance();
            List<HumanBeing> humanList = hashtableManager.getValues().stream().toList();

            List<String> xmlList = new ArrayList<>();
            humanList.forEach((person) -> {
                try {
                    xmlList.add(xmlMapper.writeValueAsString(person));
                } catch (JsonProcessingException e) {
                }
            });
            fileWriter.write("<Persons>");
            xmlList.forEach((person) -> fileWriter.write(person));
            fileWriter.write("</Persons>");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            outputModule.writeException(e.getMessage());
        }
    }
}
