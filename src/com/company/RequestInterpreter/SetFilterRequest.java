package com.company.RequestInterpreter;

import com.company.Database.OfflineDatabase;

/**
 * SetFilterRequest
 * sets the filter for the database's response
 */
public class SetFilterRequest implements Request {

    /**
     * Attributes
     */
    private OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public SetFilterRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * takes the inputted desired filter string and passes that to the database for it to set the filter
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        offlineDatabase.setFilter(args.trim());
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return String.format("Set what criteria you would like to search by - {search filter %s}", offlineDatabase.getAvailableFilterTypes());
    }

    
}