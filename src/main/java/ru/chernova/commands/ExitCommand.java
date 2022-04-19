package ru.chernova.commands;

import ru.chernova.exceptions.WrongAmountOfElementsException;

/**
 * class for command 'exit' to exit from the program
 */
public class ExitCommand extends AbstractCommand{
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
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
            System.out.println("You have entered exit command. The program will be stopped.");
            System.exit(0);
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        }
        return true;
    }
}
