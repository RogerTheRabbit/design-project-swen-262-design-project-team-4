package com.company.RequestInterpreter.Requests;

import com.company.Database.OfflineDatabase;
import com.company.RequestInterpreter.CommandHandler;

/**
 * RemoveFromLibraryRequest implements the Request class and removes
 * Searchables from your personal library based on the strings you enter
 */
public class RemoveFromLibraryRequest implements Request {

    /**
     * Attributes
     */
    private CommandHandler commandHandler;

    /**
     * Constructor
     */
    public RemoveFromLibraryRequest(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    /**
     * handles removing a searchable from the user's library
     * @param args  the guid of the searchable to be removed
     * @return      nothing currently
     */
    @Override
    public Response handle(String args) {
        
        if (commandHandler.removeSearchableFromLibrary(args)) {
            System.out.println("Removing from your personal Library!");
        } else {
            System.err.println("Failed to remove item from your personal library...");
        }
        
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Remove a song from your personal library - {GUID}";
    }

    
}