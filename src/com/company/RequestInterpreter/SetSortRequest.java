package com.company.RequestInterpreter;

import com.company.Database.OfflineDatabase;

/**
 * setSortRequest
 * sets the sorting of the info done by the database
 */
public class SetSortRequest implements Request {

    /**
     * Attributes
     */
    private OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public SetSortRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * takes the inputted desired sort string and passes that to the database for it to set the sorting method
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        
        offlineDatabase.setSort(args.trim());

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return String.format("Set how you would like your search results to be ordered - {sort by %s}", offlineDatabase.getAvailableSortTypes());
    }
}