package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * RemoveFromLibraryRequest
 */
public class RemoveFromLibraryRequest implements Request {

    private Database database;

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
        return "[GUID]";
    }

    
}