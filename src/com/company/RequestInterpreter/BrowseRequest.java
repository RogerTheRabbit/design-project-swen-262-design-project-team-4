package com.company.RequestInterpreter;

import com.company.Database.OfflineDatabase;

/**
 * BrowseRequest implements the Request class.
 * It is a request from the user to browse for an artist within the database
 */
public class BrowseRequest implements Request {

    /**
     * Attributes
     */
    OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public BrowseRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * handles the request to browse the user library
     * by looping through the artists contained in the library and printing them out
     * @param args  does nothing with the params due to the nature of browse
     * @return      currently returns nothing
     */
    @Override
    public Response handle(String args) {
        int counter = 0;
        for(String artistGUID : offlineDatabase.getArtistMap().keySet()) {
            System.out.println(offlineDatabase.getArtist(artistGUID));
            counter++;
        }

        System.out.printf("%d results found.\n", counter);

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Browse all music content in your library - No params";
    }
    
}