package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * SearchDatabaseRequest
 */
public class SearchDatabaseRequest implements Request {

    private Database database;

    public SearchDatabaseRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {

        System.out.println(database.getSongs().values());
        return null;
    }

    @Override
    public String getUsageDesc() {
        return null;
    }

    
}