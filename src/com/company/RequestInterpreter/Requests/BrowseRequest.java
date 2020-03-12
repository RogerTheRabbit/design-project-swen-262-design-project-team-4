package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * BrowseRequest implements the Request class.
 * It is a request from the user to browse for an artist within the database
 */
public class BrowseRequest implements Request {

    /**
     * Attributes
     */
    Database database;

    /**
     * Constructor
     */
    public BrowseRequest(Database database) {
        this.database = database;
    }

    /**
     * handles the request to browse the user library
     * by looping through the artists contained in the library and printing them out
     * @param args  does nothing with the params due to the nature of browse
     * @return      currently returns nothing
     */
    @Override
    public Response handle(String args) {
        
        for(String artistGUID : database.getArtistMap().keySet()) {
            System.out.println(database.getArtist(artistGUID));
        }

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "No params";
    }
    
}