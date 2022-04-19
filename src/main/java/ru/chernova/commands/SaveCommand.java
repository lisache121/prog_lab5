package ru.chernova.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.chernova.data.Dragon;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;
import ru.chernova.helpers.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

/**
 * class for command 'save' to save
 */
public class SaveCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final Parser parser;
    private final String filename;
    public SaveCommand(CollectionManager collectionManager, Parser parser, String filename) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.filename = filename;
        this.parser =parser;
    }

    /**
     *
     * @return if command successfully executed
     * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        try{
            if(this.getArguments().length > 1){
                throw new WrongAmountOfElementsException();
            }
            ArrayDeque<Dragon> dragons = collectionManager.getCollection();

            File f = new File(filename);
            boolean flag = f.canWrite();
            if (!flag){
                System.out.println("cant");
                File newFile = new File(System.getProperty("user.dir") + File.separator + "NewXml.xml");
                try
                {
                    boolean created = newFile.createNewFile();
                    if(created)
                        System.out.println("New file for saving collection has been created");
                    parser.writeStringToFile(newFile.getPath(), dragons);
                    System.out.println("Collection was successfully saved to file " + newFile);
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            } else{
                parser.writeStringToFile(filename, dragons);
                System.out.println("Collection was successfully saved to file " + filename);
            }
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        } catch (JsonProcessingException e) {
            System.out.println("Saving process failed.");
        } catch (IOException e) {
            System.out.println("Collection cannot be saved to file");
        }
        return true;
    }
}
