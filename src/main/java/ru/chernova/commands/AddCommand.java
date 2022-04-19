package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.InvalidArgumentsOfCoordinatesException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;
import ru.chernova.helpers.DragonMaker;
import java.util.Date;

/**
 * class for command 'add' to add elements to collection
 */
public class AddCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final DragonMaker maker;

    public AddCommand(CollectionManager collectionManager, DragonMaker maker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.maker = maker;
    }

    /**
     *
     * @return if command successfully executed
     *      * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        try{
            if(this.getArguments().length > 1){
                throw new WrongAmountOfElementsException();
            }
            Dragon d = new Dragon(collectionManager.makeID(),
                    maker.makeName(),
                    maker.makeCoordinates(),
                    new Date(), maker.makeAge(), maker.makeDescription(), maker.makeColor(), maker.makeType(), maker.makeHead());
            collectionManager.addToCollection(d);
            collectionManager.sort();
            System.out.println("Element successfully added.");
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        } catch (InvalidArgumentsOfCoordinatesException e){
            System.out.println("Invalid value of coordinates.");
        } catch (EmptyCollectionException e) {
            System.out.println("Collection is empty");
        }
        return true;
    }


}
