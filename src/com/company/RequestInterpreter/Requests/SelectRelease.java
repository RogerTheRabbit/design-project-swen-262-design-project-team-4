package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest
 */
public class SelectRelease implements Request {

    private Database database;

    public SelectRelease(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        
        Searchable output = database.getReleaseFromLibrary(args);

        if (output != null) {
            System.out.println(output.getSongList());
        } else {
            System.out.println("This release was not found in your personal library");
        }    

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Selects for the release in your personal library; {GUID of release to select [GUID]}";
    }

    
}