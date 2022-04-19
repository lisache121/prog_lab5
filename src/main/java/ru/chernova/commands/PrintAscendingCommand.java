package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;

import java.util.ArrayList;

/**
 * class for command 'print_ascending' to print elements in ascending order
 */
public class PrintAscendingCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
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
            if (this.getArguments().length > 1) throw new WrongAmountOfElementsException();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            ArrayList<Dragon> dragons = collectionManager.sort();
            System.out.println("Elements in collection in ascending order:");
            for (Dragon d: dragons){
                System.out.println(d.toConsole());
            }
            return true;
        } catch (EmptyCollectionException e) {
            System.out.println("Collection is empty.");
        } catch (WrongAmountOfElementsException e){
            System.out.println("This command " + getName() + "must have one argument.");
        }
        return false;
    }
}
