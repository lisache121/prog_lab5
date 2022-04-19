package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.ElementNotFoundException;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.InvalidArgumentsOfCoordinatesException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;
import ru.chernova.helpers.DragonMaker;

import java.util.Date;

/**
 * class for command 'update' to update element in collection by id
 */
public class UpdateByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final DragonMaker maker;
    private long id;

    public UpdateByIdCommand(CollectionManager collectionManager, DragonMaker maker) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.maker = maker;
    }

    /**
     *
     *@return if command successfully executed
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
                    String question = "Would you like to update fields of dragon with this id? Enter YES or NO";
                    if (maker.makeQuestion(question)) {
                        collectionManager.removeFromCollection(d);
                        collectionManager.addToCollection(new Dragon(id, maker.makeName(), maker.makeCoordinates(),
                                new Date(), maker.makeAge(), maker.makeDescription(), maker.makeColor(), maker.makeType(), maker.makeHead()));
                        System.out.println("Dragon with id " + id + " successfully updated");
                        collectionManager.sort();
                        return true;
                    } else {
                        System.out.println("Update failed.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID value must be long.");
                } catch (ElementNotFoundException e) {
                    System.out.println("Element with this id does not exist.");
                } catch (InvalidArgumentsOfCoordinatesException e) {
                    System.out.println("Invalid value of coordinates.");
                } catch (EmptyCollectionException e) {
                    System.out.println("Collection is empty. Update failed.");
                }
            }
            else throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException e) {
            System.out.println("Update command must have two arguments.");
        }
        return false;
    }
}
