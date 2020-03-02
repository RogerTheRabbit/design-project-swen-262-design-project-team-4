package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * RemoveFromLibraryRequest implements the Request class and removes
 * Searchables from your personal library based on the strings you enter
 */
public class RemoveFromLibraryRequest implements Request {

    private Database database;

    /**
     * Constructor
     */
    public RemoveFromLibraryRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        System.out.println("Removing from your personal Library!");
        
        database.removeSearchableFromLibrary(args);
        
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "{GUID}";
    }

    
}