package com.company.RequestInterpreter;

import java.util.LinkedList;
import java.util.List;

import com.company.Database.OfflineDatabase;
import com.company.Database.Searchable;

/**
 * SearchLibraryRequest implements the Request class.
 * It takes in a string from the user and searches their library for that item.
 * Handles any invalid parameters given.
 */
public class SearchLibraryRequest implements Request {

    /**
     * Attributes
     */
    OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public SearchLibraryRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * searches the user's library for specified searchables depending on what filter is set within the database
     * @param args  the search parameters to query the library
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();

        String[] params = args.split(" ");

        if(params.length < 2) {
            System.err.println("Invalid number of params.  Must use 2 params: {search type (song, release)} {search value}");
            return null;
        }

        String searchType = params[0];
        // Rest of string is song
        String searchValue = args.substring(params[0].length() + 1);

        // Get data
        switch (searchType) {
            case "song":
                songs.addAll(offlineDatabase.getSongsFromLibrary(searchValue.trim()));
                break;
            case "release":
                songs.addAll(offlineDatabase.getReleasesFromLibrary(searchValue.trim()));
                break;
            case "artist":
                songs.addAll(offlineDatabase.getArtistsFromLibrary(searchValue.trim()));
                break;
            default:
                System.err.println("Invalid search type. Please specify either 'song' or 'release' or 'artist'.");
                return null;
        }
        
        System.out.println(songs);
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Search your personal library for songs, releases or artists - {search type [song, release, artist]} {search value}";
    }

    
}