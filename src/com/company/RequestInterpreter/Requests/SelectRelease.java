package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest
 */
public class SelectRelease implements Request {

    /**
     * Attributes
     */
    private Database database;

    /**
     * Constructor
     */
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

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Selects for the release in your personal library; {GUID of release to select [GUID]}";
    }

    
}