package com.company.RequestInterpreter.Sorts;

import com.company.Database.Searchable;

/**
 * Sort that sorts based on a searchable's acquisition date.
 */
public class AcquisitionDate implements Sort {

    /**
     * Returns positive number if o1's acquisition date is
     * greater than o2's acquisition date. Returns a negative 
     * number of the opposite is true and returns 0 if 
     * acquisition date's are equal.
     */
    @Override
    public int compare(Searchable o1, Searchable o2) {
        if(o1.getAcquisitionDate() == null || o2.getAcquisitionDate() == null) {
            return 0;
        }
        return o1.getAcquisitionDate().compareTo(o2.getAcquisitionDate());
    }

}
