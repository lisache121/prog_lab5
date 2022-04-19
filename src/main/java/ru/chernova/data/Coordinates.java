package ru.chernova.data;

import ru.chernova.exceptions.InvalidArgumentsOfCoordinatesException;

import java.util.Objects;

/**
 * class for coordinates values (x, y)
 */
public class Coordinates {

    private long x; //Значение поля должно быть больше -417
    private int y; //Значение поля должно быть больше -225

    public Coordinates(long x, int y)  {
        try{
            if (x > -417 && y > -225) {
                this.x = x;
                this.y = y;
            }
            else {
                throw new InvalidArgumentsOfCoordinatesException();
            }
        } catch (InvalidArgumentsOfCoordinatesException e){
            System.out.println("X value must be greater than -417. Y value must be greater than -225.");
        }

    }
    public Coordinates(){}

    /**
     *
     * @return X coordinate
     */
    public long getX() {
        return x;
    }

    public void setX(long x) throws InvalidArgumentsOfCoordinatesException {
        try{
            if (x > -417) {
                this.x = x;
            }
            else {
                throw new InvalidArgumentsOfCoordinatesException();
            }
        } catch (InvalidArgumentsOfCoordinatesException e){
            System.out.println("X value must be greater than -417.");
        }

    }

    /**
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    public void setY(int y) throws InvalidArgumentsOfCoordinatesException {
        try{
            if (y > -225) {
                this.y = y;
            }
            else {
                throw new InvalidArgumentsOfCoordinatesException();
            }
        } catch (InvalidArgumentsOfCoordinatesException e){
            System.out.println("Y value must be greater than -225.");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return x==((Coordinates) o).getX() && y == ((Coordinates) o).getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public String toConsole(){
        return "x = " + x + ", y = " + y;
    }
}
