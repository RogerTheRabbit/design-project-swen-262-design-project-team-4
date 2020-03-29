package com.company.RequestInterpreter.Requests;

import com.company.Database.OfflineDatabase;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.CommandHandler;

/**
 * SelectRequest for a song in the user's library
 * basically it just get's the rest of a song's info when selected by guid
 */
public class SelectSong implements Request {

    /**
     * Attributes
     */
    private CommandHandler commandHandler;

    /**
     * Constructor
     */
    public SelectSong(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    /**
     * gets the song's info
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(final String args) {
        final Searchable output = commandHandler.getSongFromLibrary(args);

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