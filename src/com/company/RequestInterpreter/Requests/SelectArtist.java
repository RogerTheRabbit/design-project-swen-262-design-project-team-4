package com.company.RequestInterpreter.Requests;

import java.util.Collection;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest
 */
public class SelectArtist implements Request {

    /**
     * Attributes
     */
    Database database;

    /**
     * Constructor
     */
    public SelectArtist(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        
        Collection<Searchable> output = database.getArtistFromLibrary(args);

        if (output != null) {
            System.out.println(output);
        } else {
            System.out.println("This artist was not found in your personal library");
        }


        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Selects artist in your personal library {GUID of artist to select [GUID]}";
    }

    
}