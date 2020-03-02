package com.company.RequestInterpreter.Requests;

import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;

/**
 * SearchLibraryRequest implements the Request class.
 * It takes in a string from the user and searches their library for that item.
 * Handles any invalid parameters given.
 */
public class SearchLibraryRequest implements Request {

    Database database;

    /**
     * Constructor
     */
    public SearchLibraryRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();

        String[] params = args.split(" ");

        if(params.length < 2) {
            System.err.println("Invalid number of params.  Must use 2 params: [search type (song, release)] [search value]");
            return null;
        }

        String searchType = params[0];
        // Rest of string is song
        String searchValue = args.substring(params[0].length() + 1);

        // Get data
        switch (searchType) {
            case "song":
                songs.addAll(database.getSongsFromLibrary(searchValue.trim()));
                break;
            case "release":
                songs.addAll(database.getReleasesFromLibrary(searchValue.trim()));
                break;
            case "artist":
                songs.addAll(database.getArtistsFromLibrary(searchValue.trim()));
                break;
            default:
                System.err.println("Invalid search type. Please specify either 'song' or 'release' or 'artist'.");
                return null;
        }
        
        System.out.println(songs);
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "(search type [song, release, artist]) []";
    }

    
}