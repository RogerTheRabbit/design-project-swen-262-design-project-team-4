package com.company.RequestInterpreter.Requests;

import java.util.Collection;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest
 */
public class SelectArtist implements Request {

    Database database;

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

    @Override
    public String getUsageDesc() {
        return "Selects artist in your personal library {GUID of artist to select [GUID]}";
    }

    
}