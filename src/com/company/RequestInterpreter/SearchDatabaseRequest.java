package com.company.RequestInterpreter;

import java.util.LinkedList;
import java.util.List;

import com.company.Database.OfflineDatabase;
import com.company.Database.Searchable;

/**
 * SearchDatabaseRequest implements the Request class.
 * It takes in a string from the user to search for in the database.
 * It handles any invalid parameters given.
 */
public class SearchDatabaseRequest implements Request {

    /**
     * Attributes
     */
    private OfflineDatabase offlineDatabase;

    /**
     * Constructor
     */
    public SearchDatabaseRequest(OfflineDatabase offlineDatabase) {
        this.offlineDatabase = offlineDatabase;
    }

    /**
     * searches the database for specified searchables depending on what filter is set within the database
     * @param args  the search parameters to query the database
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();

        String[] params = args.split(" ");

        if(params.length < 2) {
            System.err.println("Invalid number of params.  Must use 2 params: [search type (song, release, artist)] [search value]");
            return null;
        }

        String searchType = params[0];
        // Rest of string is song
        String searchValue = args.substring(params[0].length() + 1);

        // Get data
        switch (searchType) {
            case "song":
                songs.addAll(offlineDatabase.getSongs(searchValue.trim()));
                break;
            case "release":
                songs.addAll(offlineDatabase.getReleases(searchValue.trim()));
                break;
            case "artist":
                songs.addAll(offlineDatabase.getArtists(searchValue.trim()));
                break;
            default:
                System.err.println("Invalid search type. Please specify either 'song' or 'release'.");
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
        return "Search the database for a song, release, or artist. The default filter being searching by name - {search type (song, release, artist)} {search value}";
    }

}
