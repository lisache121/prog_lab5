package ru.chernova.helpers;

import ru.chernova.data.*;
import ru.chernova.exceptions.*;

import java.util.InputMismatchException;

import java.util.Scanner;

/**
 * class for making new elements of collection
 */
public class DragonMaker {
    private final Dragon dragon= new Dragon();
    private final Scanner scanner = new Scanner(System.in);
    private final Coordinates coordinates = new Coordinates();
    private final DragonHead head = new DragonHead();

    public DragonMaker() {
    }

    public Dragon getDragon() {
        return dragon;
    }

    /**
     *
     * @return String with new name
     */
    public String makeName(){
        String name;
        boolean repeat = true;
        while(repeat) {
            try{
                System.out.println("Enter dragon's name. Name value must be string.");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) throw new EmptyFieldException();
                dragon.setName(name);
                repeat = false;
            } catch (EmptyFieldException e) {
                System.err.println("Name value cannot be empty. Try again.");
            } catch (InputMismatchException e){
                System.err.println("Name value must be string. Try again.");
            }
        }
        return dragon.getName();
    }

    /**
     *
     * @return new x coordinate
     */
    public long makeX(){
        String xStr;
        long x;
        boolean repeat = true;
        while (repeat) {
            try{
                System.out.println("Enter x coordinate. X value must be a number, greater than -417");
                xStr = scanner.nextLine().trim();
                if (xStr.isEmpty()) throw new EmptyFieldException();
                x = Long.parseLong(xStr);
                coordinates.setX(x);
                repeat = false;
            }catch (EmptyFieldException e) {
                System.err.println("X coordinate must not be empty.");
            } catch (InvalidArgumentsOfCoordinatesException e) {
                System.err.println("X value must be greater than -417. Try again.");
            } catch (NumberFormatException e){
                System.err.println("X coordinate must be a number. Type long. Try again.");
            }
        }
        return coordinates.getX();
    }

    /**
     *
     * @return new y coordinate
     */
    public int makeY(){
        String yStr;
        int y;
        boolean repeat = true;
        while (repeat) {
            try{
                System.out.println("Enter Y coordinate. Y value must be integer, greater than -225");
                yStr = scanner.nextLine().trim();
                if (yStr.isEmpty()) throw new EmptyFieldException();
                y = Integer.parseInt(yStr);
                coordinates.setY(y);
                repeat = false;
            } catch (InvalidArgumentsOfCoordinatesException e) {
                System.err.println("Y value must be greater than -225. Try again.");
            } catch (EmptyFieldException e) {
                System.err.println("Y coordinate cannot be empty");
            }catch (NumberFormatException e){
                System.err.println("Y coordinate must be integer. Try again.");
            }
        }
        return coordinates.getY();
    }

    /**
     *
     * @return new instance of Coordinates
     * @throws InvalidArgumentsOfCoordinatesException if x or y are incorrect
     */
    public Coordinates makeCoordinates() throws InvalidArgumentsOfCoordinatesException {
        long x;
        int y;
        x = makeX();
        y = makeY();
        return new Coordinates(x, y);
    }

    /**
     *
     * @return new age of element
     */
    public int makeAge(){
        String ageStr;
        int age;
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.println("Enter dragon's age. Age value must be greater than 0.");
                ageStr = scanner.nextLine().trim();
                if (ageStr.isEmpty()) throw new EmptyFieldException();
                age = Integer.parseInt(ageStr);
                dragon.setAge(age);
                repeat = false;
            } catch (NumberFormatException e) {
                System.err.println("Age value must be integer. Try again.");
            } catch (EmptyFieldException e) {
                System.err.println("Age cannot be empty");
            } catch (InvalidAgeInputException e) {
                System.out.println("Age must be greater than 0.");
            }
        }
        return dragon.getAge();
    }

    /**
     *
     * @return String with description of element
     */
    public String makeDescription(){
        String description;
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.println("Enter description of the dragon. It must not be empty");
                description = scanner.nextLine().trim();
                if (description.isEmpty()) throw new EmptyFieldException();
                dragon.setDescription(description);
                repeat = false;
            } catch (EmptyFieldException e) {
                System.err.println("Description value must not be empty. Try again.");
            } catch (InputMismatchException e){
                System.err.println("Description value must be string. Try again.");
            }catch (NullPointerException e){
                System.err.println("Description cannot be null");
            }
        }
        return dragon.getDescription();
    }

    /**
     *
     * @return color of element
     */
    public Color makeColor(){
        Color color;
        String colorStr;
        boolean repeat = true;
        while (repeat){
            try{
                boolean flag = makeQuestion("Would you like to enter color of the dragon? Answer must be 'YES' or 'NO'");
                if (flag){
                System.out.println("Enter the color of the dragon from list: " + Color.nameList());
                colorStr = scanner.nextLine().trim();
                color = Color.valueOf(colorStr.toUpperCase());
                dragon.setColor(color);
                }
                else{
                    dragon.setColor(null);
                }
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.err.println("Color must be from list of colors or null. Try again.");
            }
        }
        return dragon.getColor();
    }

    /**
     *
     * @return new type of element
     */
    public DragonType makeType(){
        DragonType type;
        String typeStr;
        boolean repeat = true;
        while (repeat) {
            try{
                boolean flag = makeQuestion("Would you like to enter type of the dragon? Answer must be 'YES' or 'NO'");
                if (flag){
                    System.out.println("Enter type of the dragon from list: " + DragonType.nameList());
                    typeStr = scanner.nextLine().trim();
                    type = DragonType.valueOf(typeStr.toUpperCase());
                    dragon.setType(type);
                }
                else{
                    dragon.setType(null);
                }
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.err.println("Type must be from type list. Try again.");
            }
        }
        return dragon.getType();
    }

    /**
     *
     * @return new size of head
     */
    public Long makeSize(){
        long size;
        String sizeStr;
        boolean repeat = true;
        while (repeat) {
            try{
                boolean flag = makeQuestion("Would you like to enter size of the dragon? Answer must be 'YES' or 'NO'");
                if (flag){
                    System.out.println("Enter size of a dragon. It must be a number (long).");
                    sizeStr = scanner.nextLine().trim();
                    size = Long.parseLong(sizeStr);
                    head.setSize(size);

                } else{
                    head.setSize(null);
                }
                repeat = false;
            } catch (NumberFormatException e) {
                System.err.println("Size must be a number (long). Try again.");
            }
        }
        return head.getSize();
    }

    /**
     *
     * @return new eyes count
     */
    public Float makeEyesCount(){
        float eyesCount;
        String countStr;
        boolean repeat = true;
        while (repeat) {
            try{
                System.out.println("Enter count of eyes of a dragon. It must be a number (float).");
                countStr = scanner.nextLine().trim();
                if (countStr.isEmpty()) throw new EmptyFieldException();
                eyesCount = Float.parseFloat(countStr);
                head.setEyesCount(eyesCount);
                repeat = false;
            } catch (NumberFormatException e) {
                System.err.println("Size must be a number (float). Try again.");
            } catch (EmptyFieldException e){
                System.out.println("Eyescount value must not be empty. Try again.");
            }
        }
        return head.getEyesCount();
    }

    /**
     *
     * @return new tooth count
     */
    public Long makeToothCount(){
        long toothCount;
        String countStr;
        boolean repeat = true;
        while (repeat) {
            try{
                boolean flag = makeQuestion("Would you like to enter tooth count of the dragon? Answer must be 'YES' or 'NO'");
                if (flag){
                    System.out.println("Enter count of tooth of a dragon. It must be a number (long).");
                    countStr = scanner.nextLine().trim();
                    toothCount = Long.parseLong(countStr);
                    head.setToothCount(toothCount);
                } else{
                    head.setSize(null);
                }
                repeat = false;
            } catch (NumberFormatException e) {
                System.err.println("Tooth count must be a number (long). Try again.");
            }
        }
        return head.getToothCount();
    }

    /**
     * return new DragonHead
     * @return
     */
    public DragonHead makeHead(){
        return new DragonHead(makeSize(), makeEyesCount(), makeToothCount());
    }

    /**
     * method for asking user to enter fields
     * @param question String with question
     * @return user's answer
     */
    public boolean makeQuestion(String question) {
        String ans;
        while (true){
            try {
                System.out.println(question);
                ans = scanner.nextLine().trim();
                if (!ans.equals("YES") && !ans.equals("NO")) throw new WrongInputException();
                break;
            } catch (WrongInputException e) {
                System.err.println("Answer must be 'YES' or 'NO'");
            }
        }
        return ans.equals("YES");
    }


}
