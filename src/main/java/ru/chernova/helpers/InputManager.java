package ru.chernova.helpers;

import ru.chernova.commands.*;
import ru.chernova.data.Dragon;
import ru.chernova.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * class for executing commands in interactive and script mode
 */
public class InputManager {
    private final List<String> scriptHistory = new ArrayList<>();
    private final String filename;
    private final Comparator comparator = new Comparator();
    private final Parser parser = new Parser();

    /**
     *
     * @param filename the path to file from which we read and to which we write collection data
     */
    public InputManager(String filename) {
        this.filename = filename;
    }

    /**
     * method to read collection from file
     */
    public void run(CollectionManager collectionManager, DragonMaker maker) throws  WrongAmountOfElementsException {
        try {
            File f = new File(filename);
            if(!f.isDirectory() && Files.isReadable(f.toPath())){
                ArrayDeque<Dragon> d = parser.fromXmlToCollectionManager(parser.fromXmlToString(filename));
                collectionManager.setCollection(d);
                collectionManager.sort();
            }else{
                System.out.println("There is no rights for reading file. Change rights and run program again!");
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("File path is wrong. Run program again with correct filename.");
            System.exit(0);
        } catch(NoSuchElementException e){
            System.out.println("You have entered the end of file symbol. Program will be stopped.");
            System.exit(0);
        } catch (EmptyCollectionException e) {
            System.out.println("Collection is empty");
        }
        Map<String, AbstractCommand> commands = new HashMap<>();

        commands.put("add", new AddCommand(collectionManager, maker));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, maker));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager, maker));
        commands.put("filter_less_than_age", new FilterLessThanAgeCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("update", new UpdateByIdCommand(collectionManager, maker));
        commands.put("remove_head", new RemoveHeadCommand(collectionManager));
        commands.put("max_by_description", new MaxByDescriptionCommand(collectionManager));
        commands.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager, parser, filename));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
        commands.put("clear", new ClearCommand(collectionManager));

        interactiveMod(parser, commands, scriptHistory, "0");

    }
    private boolean fileMode = false;

    /**
     *
     * @param parser Parser instance
     * @param commands Map of all commands to recognize them by key
     * @param scriptHistory List of all files executed by command execute_script
     * @param scriptName file for execute_script command
     * @throws WrongAmountOfElementsException exception to check command arguments
     */
    public void interactiveMod(Parser parser, Map<String, AbstractCommand> commands, List<String> scriptHistory, String scriptName) throws WrongAmountOfElementsException {

        Scanner scanner = new Scanner(System.in);
        try {
            File f = new File(scriptName);
            if (f.exists()) {
                scanner = new Scanner(Paths.get(scriptName));
                fileMode = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (fileMode) {
                    if (!scanner.hasNextLine()) {
                        fileMode = false;
                        scanner = new Scanner(System.in);
                        scriptHistory.clear();
                        System.out.println("The end of script");
                    }
                }
                System.out.println(">");
                if (!fileMode) System.out.println(" Enter next command");
                String lineScan = scanner.nextLine().trim();
                if (fileMode) {
                    System.out.println("Command from file - " + lineScan);
                }
                String[] line = lineScan.split(" ");
                if (line.length > 0) {
                    try {
                        if (commands.containsKey(line[0])) {
                            AbstractCommand command = commands.get(line[0]);
                            command.setArguments(line);
                            command.execute();
                        }else{
                            throw new WrongCommandException();
                        }
                        if(line[0].equals("execute_script")){
                            if (scriptMod(line[1], scriptHistory)){
                                interactiveMod(parser, commands, scriptHistory, line[1]);
                                fileMode = true;
                            }
                        }

                    } catch (WrongCommandException e) {
                        if (fileMode){
                            System.out.println("You have entered wrong command name in file");
                        }
                        else{
                            System.out.println("You have entered wrong command name");
                        }
                    }

                }
            }  catch(NoSuchElementException e){
                System.out.println("You have entered the end of file symbol. Program will be terminate and collection will be saved.");
                commands.get("save").setArguments(new String[]{"save"});
                commands.get("save").execute();
                System.exit(0);
            }
        }



    }

    /**
     *
     * @param scriptFile file for checking
     * @param scriptHistory List of all files executed by command execute_script
     * @return true if file is correct
     */
    public boolean scriptMod(String scriptFile, List<String> scriptHistory){

            try {
                if (new File(scriptFile).isDirectory() ) {
                    System.out.println("Filename cannot be a directory.");
                }
                if (!Files.isReadable(new File(scriptFile).toPath())){
                    System.out.println("There is no rights for reading file.");
                }
                Scanner scanner = new Scanner(Paths.get(scriptFile));
                if (!new File(scriptFile).exists()) throw new FileNotFoundException();
                if (!scanner.hasNext())
                    throw new NoSuchElementException();
                for (String scripts : scriptHistory) {
                    if (scriptFile.equals(scripts)) throw new RecursionException();
                }
                scriptHistory.add(scriptFile);
//                fileMode = true;
                return true;
            } catch (FileNotFoundException exception) {
                System.out.println("There is no file with this filename");
            } catch (NoSuchElementException exception) {

                System.out.println("This file is empty");

            } catch (RecursionException e) {
                System.out.println("You cannot execute script recursively.");
            } catch (IllegalStateException exception) {
                System.out.println("Unpredictable error.");
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Run program again with correct filename.");
            }
        return false;

}}
