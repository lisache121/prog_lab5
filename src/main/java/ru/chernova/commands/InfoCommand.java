package ru.chernova.commands;


import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;

/**
 * class for command 'info' to print information about collection
 */
public class InfoCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
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
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            String str = "Type of collection: " + collectionManager.getType() +'\n' + "Date of initialisation: " +
                    collectionManager.getTime() + '\n' + "Number of elements: " + collectionManager.getArraySize();
            System.out.println(str);
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        }  catch (EmptyCollectionException e) {
            System.out.println("Collection is empty");
        }
        return true;
    }
}
