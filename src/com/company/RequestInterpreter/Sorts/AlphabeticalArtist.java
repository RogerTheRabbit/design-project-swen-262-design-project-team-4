package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

public class AlphabeticalArtist implements Sort {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }

}
