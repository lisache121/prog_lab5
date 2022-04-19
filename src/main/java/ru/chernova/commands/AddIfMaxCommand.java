package ru.chernova.commands;

import ru.chernova.data.Dragon;
import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.InvalidArgumentsOfCoordinatesException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;
import ru.chernova.helpers.DragonMaker;

import java.util.Date;

/**
 * class for command 'add_if_max' add element if is greater than other elements in collection
 */
public class AddIfMaxCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final DragonMaker maker;

    public AddIfMaxCommand(CollectionManager collectionManager, DragonMaker maker) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.maker = maker;
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
            Dragon d = (new Dragon(collectionManager.makeID(), maker.makeName(), maker.makeCoordinates(),
                    new Date(), maker.makeAge(), maker.makeDescription(), maker.makeColor(), maker.makeType(), maker.makeHead()));
            if (collectionManager.getCollection().size()==0) {
                throw new EmptyCollectionException();
            }
            else{
                if (d.getAge() > collectionManager.maxByAge()){
                    collectionManager.addToCollection(d);
                    return true;
                }
            }
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        } catch (InvalidArgumentsOfCoordinatesException e){
            System.out.println("Invalid value of coordinates.");
        }catch (EmptyCollectionException e){
            System.out.println("Collection is empty. New element cannot be compared to anything.");
        }
        return false;
    }
}
