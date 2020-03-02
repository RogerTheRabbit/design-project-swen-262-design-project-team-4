package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SelectRequest
 */
public class SelectSong implements Request {

    private Database database;

    public SelectSong(final Database database) {
        this.database = database;
    }

    @Override
    public Response handle(final String args) {
        final Searchable output = database.getSongFromLibrary(args);

        if (output != null) {
            System.out.println(output);
        } else {
            System.out.println("This song was not found in your personal library");
        }


        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Selects the song in your personal library; {GUID of song to select [GUID]}";
    }

}