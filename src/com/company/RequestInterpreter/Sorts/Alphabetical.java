package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

/**
 * Sort used to sort Lexicographically based on a Searchable's name
 */
public class Alphabetical implements Sort {

    /**
     * Uses String's compareTo to compare the Name of o1 to the name of o2
     */
    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }

}
