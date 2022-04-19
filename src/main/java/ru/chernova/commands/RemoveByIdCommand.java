package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.ElementNotFoundException;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;


/**
 * class for command 'remove_by_id
 */
public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private long id;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    /**
     *
     * @return if command successfully executed
     * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        String[] arguments = getArguments();
        try {
            if (arguments.length == 2) {
                try {
                    if (collectionManager.getCollection().size()==0) throw new EmptyCollectionException();
                    this.id = Long.parseLong(arguments[1]);
                    Dragon d = collectionManager.getById(id);
                    if (d == null) throw new ElementNotFoundException();
                    collectionManager.removeFromCollection(d);
                    collectionManager.sort();
                    System.out.println("Element with id " + id + " was successfully removed.");
                } catch (NumberFormatException e) {
                    System.out.println("ID value must be long.");
                } catch (ElementNotFoundException e) {
                    System.out.println("Element with this id does not exist.");
                } catch (EmptyCollectionException e) {
                    System.out.println("Collection is empty. Removing failed.");
                }
            }
            else throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException e) {
            System.out.println("Update command must have two arguments.");
        }
        return false;
    }
}
