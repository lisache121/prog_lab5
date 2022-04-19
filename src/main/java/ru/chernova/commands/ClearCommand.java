package ru.chernova.commands;


import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;


/**
 * class for command 'clear' to clear collection
 */
public class ClearCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
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
            if(this.getArguments().length != 1){
                throw new WrongAmountOfElementsException();
            }

            collectionManager.clearCollection();
            System.out.println("Collection is clear now");
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        }
        return true;
    }
}
