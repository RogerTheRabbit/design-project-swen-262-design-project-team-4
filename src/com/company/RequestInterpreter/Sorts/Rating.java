package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

/**
 * Sorts based on rating of two searchables.
 */
public class Rating implements Sort {

    /**
     * Uses int's compareTo to compare the ratings
     * of o1 to o2.
     */
    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o2.getRating().compareTo(o1.getRating());
    }

}
