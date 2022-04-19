package ru.chernova.commands;


import ru.chernova.exceptions.EmptyCollectionException;
import ru.chernova.exceptions.WrongAmountOfElementsException;
import ru.chernova.helpers.CollectionManager;


/**
 * class for command 'filter_less_than_age' to filter elements in collection
 */
public class FilterLessThanAgeCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private int age;
    public FilterLessThanAgeCommand(CollectionManager collectionManager) {
        super("filter_less_than_age age", "вывести элементы, значение поля age которых меньше заданного");
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
        try {
            if (args.length != 2) throw new WrongAmountOfElementsException();
            if (collectionManager.getCollection().size()==0) throw new EmptyCollectionException();
            this.age = Integer.parseInt(args[1]);
            if (collectionManager.filterByAge(age) != null){
                System.out.println("Collection filtered by age: ");
                System.out.println(collectionManager.filterByAge(age));
            }
            else{
                System.out.println("There are no dragons in collection younger than " + age);
            }
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command " + getName() + " must have two arguments." );
        } catch (EmptyCollectionException e){
            System.out.println("Collection is empty.");
        } catch(NumberFormatException e){
            System.out.println("Age value must be integer.");
        }
        return false;
    }
}
