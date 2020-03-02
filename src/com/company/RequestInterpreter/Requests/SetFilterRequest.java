package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * SetFilterRequest
 */
public class SetFilterRequest implements Request {

    private Database database;

    public SetFilterRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        database.setFilter(args.trim());
        return null;
    }

    @Override
    public String getUsageDesc() {
        return String.format("{search filter [%s]}", database.getAvailableFilterTypes());
    }

    
}