package com.company.RequestInterpreter.Filters;

import com.company.Database.Library;
import com.company.Database.Searchable;

public class Rating implements Filter {

    Library library;

    public Rating(Library library) {
        this.library = library;
    }

    @Override
    public int compare(Searchable o1, Searchable o2) {
        return 0;
    }

}
