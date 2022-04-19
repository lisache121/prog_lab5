package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;
/**
 * class for command 'remove_head' to remove first element
 */
public class RemoveHeadCommand extends AbstractCommand{
    private final CollectionManager collectionManager;


    public RemoveHeadCommand(CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
    }

    /**
     *
     * @return if command successfully executed
     * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        String[] args = getArguments();
        try{
            if (args.length != 1) throw new WrongAmountOfElementsException();
            if (collectionManager.getCollection().size()==0) throw new EmptyCollectionException();
            Dragon d = collectionManager.getCollection().getFirst();
            System.out.println(d.toConsole());
            collectionManager.removeFromCollection(d);
            collectionManager.sort();
            System.out.println("Element removed successfully.");
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command " + getName() + "must have one argument.");
        } catch (EmptyCollectionException e){
            System.out.println("Collection is empty. Removing head failed.");
        }
        return false;
    }
}
