package com.company.RequestInterpreter.Requests;

import com.company.Database.OfflineDatabase;
import com.company.RequestInterpreter.CommandHandler;
import com.company.RequestInterpreter.Requests.Request;
import com.company.RequestInterpreter.Requests.Response;

/**
 * BrowseRequest implements the Request class.
 * It is a request from the user to browse for an artist within the database
 */
public class BrowseRequest implements Request {

    /**
     * Attributes
     */
    private CommandHandler commandHandler;

    /**
     * Constructor
     */
    public BrowseRequest(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
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
        /**
         *
         *
        for(String artistGUID : commandHandler.getArtistMap().keySet()) {
            System.out.println(commandHandler.getArtist(artistGUID));
            counter++;
        }
         */
        Response response = commandHandler.browseArtist();

        System.out.printf("%d results found.\n", response.getContent().size());

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