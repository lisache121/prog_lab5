package ru.chernova;
import ru.chernova.exceptions.*;
import ru.chernova.helpers.CollectionManager;
import ru.chernova.helpers.DragonMaker;
import ru.chernova.helpers.InputManager;

/**
 * Main method to run the program
 */
public class Main {
    public static void main(String[] args) throws WrongAmountOfElementsException, InvalidArgumentsOfCoordinatesException, InvalidAgeInputException, EmptyFieldException {
        DragonMaker maker = new DragonMaker();
        try{
            String path = System.getenv().get("Collection");
            CollectionManager collectionManager = new CollectionManager();
            InputManager inputManager = new InputManager(path);
            inputManager.run(collectionManager, maker);
        }catch (NullPointerException e){
            System.out.println("There is no such environment variable.");
        }



    }
}
