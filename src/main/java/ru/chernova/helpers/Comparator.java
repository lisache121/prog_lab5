package ru.chernova.helpers;

import ru.chernova.data.Dragon;

/**
 * class comparator to sort and compare
 */
class Comparator implements java.util.Comparator<Dragon>{

    @Override
    public int compare(Dragon o1, Dragon o2) {
        return o1.getAge()-o2.getAge();
    }

    /**
     *
     * @param o1 instance of Dragon
     * @param o2 instance of Dragon
     * @return comparison result
     */
    public int compareByDescription(Dragon o1, Dragon o2) {
        return o1.getDescription().length() - o2.getDescription().length();
    }
}
