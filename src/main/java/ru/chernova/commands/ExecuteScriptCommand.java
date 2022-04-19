package ru.chernova.commands;

import ru.chernova.exceptions.WrongAmountOfElementsException;

/**
 * class for command 'execute_script' to execute script
 */
public class ExecuteScriptCommand extends AbstractCommand{

    public ExecuteScriptCommand() {
        super("execute_script file_name",
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит");
    }

    /**
     *
     * @return if command successfully executed
     * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    @Override
    public boolean execute() throws WrongAmountOfElementsException {
        try {
            if (this.getArguments().length != 2) {
                throw new WrongAmountOfElementsException();
            }
            System.out.println("Executing script " + getArguments()[1]);
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command have argument - file_name.");
        }
        return false;
    }
}
