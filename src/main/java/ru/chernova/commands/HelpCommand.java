package ru.chernova.commands;


import ru.chernova.exceptions.WrongAmountOfElementsException;

import java.util.Map;

/**
 * class for command 'help'
 */
public class HelpCommand extends AbstractCommand{
    private Map<String,AbstractCommand> commands;

    public HelpCommand(Map<String,AbstractCommand> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
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
            StringBuilder string = new StringBuilder();
            string.append("The full list of commands is here: \n");
            for(AbstractCommand command: commands.values()){
                string.append(command.getName()).append(" : ").append(command.getDescription()).append("\n");
            }
            System.out.println(string.toString());
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println("This command does not have arguments.");
        }
        return true;

    }
}
