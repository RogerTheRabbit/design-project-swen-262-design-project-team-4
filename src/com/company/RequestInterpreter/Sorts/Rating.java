package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

public class Rating implements Sort {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o1.getRating().compareTo(o2.getRating());
    }

}
