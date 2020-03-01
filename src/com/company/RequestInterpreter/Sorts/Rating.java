package com.company.RequestInterpreter.Sorts;

import com.company.Database.Library;
import com.company.Database.Searchable;

public class Rating implements Sort {

    Library library;

    public Rating(Library library) {
        this.library = library;
    }

    @Override
    public int compare(Searchable o1, Searchable o2) {
        return 0;
    }

}
