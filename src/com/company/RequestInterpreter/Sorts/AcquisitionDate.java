package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

public class AcquisitionDate implements Sort {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o1.getAcquisitionDate().compareTo(o2.getAcquisitionDate());
    }

}
