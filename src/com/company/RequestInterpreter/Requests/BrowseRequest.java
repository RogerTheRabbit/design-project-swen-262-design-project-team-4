package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * BrowseRequest
 */
public class BrowseRequest implements Request {

    Database database;

    public BrowseRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        
        for(String artistGUID : database.getArtistMap().keySet()) {
            System.out.println(database.getArtist(artistGUID));
        }

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "No params";
    }
    
}