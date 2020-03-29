package com.company.ResponseFormatter.Sorts;

import java.util.Comparator;

import com.company.Database.Searchable;

/**
 * Sort interface used to define sorts. Sorts are used
 * to sort search query results before being displayed 
 * to the user.
 */
public interface Sort extends Comparator<Searchable> {
    public int compare(Searchable o1, Searchable o2);
}
