package com.company.RequestInterpreter;

import com.company.Database.OfflineDatabase;

/**
 * RemoveFromLibraryRequest implements the Request class and removes
 * Searchables from your personal library based on the strings you enter
 */
public class RemoveFromLibraryRequest implements Request {

    /**
     * Attributes
     */
    private OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public RemoveFromLibraryRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * handles removing a searchable from the user's library
     * @param args  the guid of the searchable to be removed
     * @return      nothing currently
     */
    @Override
    public Response handle(String args) {
        
        if (offlineDatabase.removeSearchableFromLibrary(args)) {
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