package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * setSortRequest
 */
public class SetSortRequest implements Request {

    /**
     * Attributes
     */
    private Database database;

    /**
     * Constructor
     */
    public SetSortRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        
        database.setSort(args.trim());

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return String.format("Set how you would like your search results to be ordered - {sort by %s}", database.getAvailableSortTypes());
    }
}