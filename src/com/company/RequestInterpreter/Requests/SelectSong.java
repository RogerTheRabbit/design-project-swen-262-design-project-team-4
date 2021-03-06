package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest for a song in the user's library
 * basically it just get's the rest of a song's info when selected by guid
 */
public class SelectSong implements Request {

    /**
     * Attributes
     */
    private Database database;

    /**
     * Constructor
     */
    public SelectSong(final Database database) {
        this.database = database;
    }

    /**
     * gets the song's info
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(final String args) {
        final Searchable output = database.getSongFromLibrary(args);

        if (output != null) {
            System.out.println(output);
        } else {
            System.out.printf("song,'%s', was not found in your personal library.\n", args);
        }
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Selects the song in your personal library - {GUID of song to select}";
    }

}