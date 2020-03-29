package com.company.RequestInterpreter.Requests;

import java.util.Collection;

import com.company.Database.OfflineDatabase;
import com.company.Database.Searchable;

/**
 * SelectRequest for the artist's contents in the user library
 */
public class SelectArtist implements Request {

    /**
     * Attributes
     */
    OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public SelectArtist(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * gets the searchable content of the selected artist
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        
        Collection<Searchable> output = offlineDatabase.getArtistFromLibrary(args);

        if (output != null) {
            System.out.println(output);
        } else {
            System.out.printf("Artist, '%s', was not found in your personal library.\n", args);
        }


        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Selects artist in your personal library and retrieves music by that artist in your library - {GUID of artist to select}";
    }

    
}