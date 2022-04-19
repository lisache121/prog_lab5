package ru.chernova.commands;

import ru.chernova.exceptions.WrongAmountOfElementsException;

/**
 * Abstract Command class contains Object methods, name and description.
 */
public abstract class AbstractCommand {
    private String[] arguments;
    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return arguments of the command
     */
    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * @return Name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return if command successfully executed
     * @throws WrongAmountOfElementsException if number of arguments is not as expected
     */
    public abstract boolean execute() throws WrongAmountOfElementsException;


}
