package ru.chernova.helpers;


import ru.chernova.data.Dragon;
import ru.chernova.exceptions.EmptyCollectionException;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;

/**
 * class to work with collection's elements
 */
public class CollectionManager {
    private ArrayDeque<Dragon> dragons;
    private final Date time = new Date();
    private Comparator comparator = new Comparator();

    public CollectionManager(){
    }

    /**
     *
     * @param dragons with collection's elements
     */
    public CollectionManager (ArrayDeque<Dragon> dragons) {
        this.dragons=dragons;
    }

    public void setTime(Date time){
    }
    public void setMyArrayType(String arrayType){}
    public ArrayDeque<Dragon> getCollection() {
        return dragons;
    }



    public String getType(){
        return "ArrayDeque";
    }
    public int getArraySize(){
        return dragons.size();
    }
    public void addToCollection(Dragon dragon){
        dragons.add(dragon);
    }

    public String getTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        return dateFormat.format(time);
    }


    public ArrayDeque<Dragon> getDragons() {
        return dragons;
    }

    public void setCollection(ArrayDeque<Dragon> dragons) {
        this.dragons = dragons;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     *
     * @return long value of created id
     */
    public long makeID(){
        try {
            if (dragons.isEmpty()) {
                return 1L;
            }

        }catch (NullPointerException e){
            System.err.println("error");
        }
        return dragons.getLast().getId() + 1;
    }

    /**
     *
     * @return minimum age value of elements in collection
     */
    public int minByAge(){
        try {
            Dragon d = dragons.getFirst();
            int minAge = d.getAge();
            for (Dragon dragon: dragons) {
                if (comparator.compare(dragon, d) < 0){
                    minAge = dragon.getAge();
                }
            }
            return minAge;
        } catch (IndexOutOfBoundsException e){
            System.err.println("Collection is empty.");

        }return 0;
    }

    /**
     *
     * @return maximum age value of elements in collection
     */
    public int maxByAge(){
        try {
            Dragon d = dragons.getFirst();
            int maxAge = d.getAge();
            for (Dragon dragon: dragons) {
                if (comparator.compare(dragon, d) > 0){
                    maxAge = dragon.getAge();
                }
            }
            return maxAge;
        } catch (IndexOutOfBoundsException e){
            System.err.println("Collection is empty.");

        }return 0;
    }

    /**
     *
     * @param id of element
     * @return element of collection with this id
     */
    public Dragon getById(long id){
        for (Dragon dragon:dragons){
            if (dragon.getId()==(id)) {
                return dragon;
            }
        }
        return null;
    }

    /**
     *
     * @param d element of collection to remove
     */
    public void removeFromCollection(Dragon d){
        dragons.remove(d);
    }

    /**
     *
     * @return String representation of element with maximum description
     * @throws EmptyCollectionException if there is no elements to print
     */
    public String maxByDescription() throws EmptyCollectionException {
        if (dragons.isEmpty()) throw new EmptyCollectionException();
        Dragon maxDragon = dragons.getFirst();
        for (Dragon dragon: dragons) {
            if (comparator.compareByDescription(dragon, maxDragon) > 0){
                maxDragon = dragon;
            }
        }
        return maxDragon.toConsole();
    }

    /**
     *
     * @param age to filter by
     * @return String with elements with age less than this age
     * @throws EmptyCollectionException if there is no elements in collection
     */
    public String filterByAge(int age) throws EmptyCollectionException {
        String filteredDragons = null;
        if (dragons.isEmpty()) throw new EmptyCollectionException();
        for (Dragon dragon: dragons){
            if (dragon.getAge() < age){
                filteredDragons += dragon.toConsole();
            }
        }
        return filteredDragons;
    }

    /**
     *
     * @return ArrayList of sorted elements by age
     * @throws EmptyCollectionException if there is no elements in collection
     */
    public ArrayList<Dragon> sort() throws EmptyCollectionException {
        if (dragons.isEmpty()) throw new EmptyCollectionException();
        ArrayList<Dragon> list= new ArrayList<>(dragons);
        list.sort(comparator);
        return list;
    }

    /**
     * prints elements in collection
     * @throws EmptyCollectionException if there is no elements in collection
     */
    public void showCollection() throws EmptyCollectionException {
        if (dragons.isEmpty()) throw new EmptyCollectionException();
        for (Dragon d: dragons){
            System.out.println(d.toConsole());
        }
    }

    /**
     * clears collection
     */
    public void clearCollection(){
        dragons.clear();
    }


}
