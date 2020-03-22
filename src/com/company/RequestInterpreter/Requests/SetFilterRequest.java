package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * SetFilterRequest
 * sets the filter for the database's response
 */
public class SetFilterRequest implements Request {

    /**
     * Attributes
     */
    private Database database;

    /**
     * Constructor
     */
    public SetFilterRequest(Database database) {
        this.database = database;
    }

    /**
     * takes the inputted desired filter string and passes that to the database for it to set the filter
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        database.setFilter(args.trim());
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return String.format("Set what criteria you would like to search by - {search filter %s}", database.getAvailableFilterTypes());
    }

    
}