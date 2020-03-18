package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest for the release's contents
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

    /**
     * gets the list of songs for the specified release
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        
        Searchable output = database.getReleaseFromLibrary(args);

        if (output != null) {
            System.out.println(output.getSongList());
        } else {
            System.out.printf("Release, '%s', was not found in your personal library.\n", args);
        }    

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Selects the release in your personal library and displays the songs in that release - {GUID of release to select}";
    }

    
}