package ru.chernova.commands;

import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;

/**
 * class for command 'show' to show elements in collection
 */
public class ShowCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    /**
     *
     *@return if command successfully executed
     *@throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        try{
            if(this.getArguments().length > 1){
                throw new WrongAmountOfElementsException();
            }
            System.out.println("Elements in collection: ");
            collectionManager.showCollection();

            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        } catch (EmptyCollectionException e) {
            System.out.println("There is no elements in collection.");
        }
        return true;
    }
}
