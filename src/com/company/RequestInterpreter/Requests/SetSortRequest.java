package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * setSortRequest
 */
public class SetSortRequest implements Request {

    private Database database;

    public SetSortRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        
        database.setSort(args.trim());

        return null;
    }

    @Override
    public String getUsageDesc() {
        return String.format("[sort by %s]", database.getAvailableSortTypes());
    }
}