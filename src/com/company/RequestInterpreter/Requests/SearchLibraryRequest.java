package com.company.RequestInterpreter.Requests;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.Sorts.AlphabeticalArtist;

/**
 * SearchLibraryRequest
 */
public class SearchLibraryRequest implements Request {

    Database database;

    public SearchLibraryRequest(Database database) {
        this.database = database;
    }

    private Comparator<Searchable> sort = new AlphabeticalArtist();

    public void setSort(Comparator<Searchable> sort) {
        this.sort = sort;
    }

    // TODO: Make it so this doesn't show artists.  Currently shows all types of searchables but should only show songs and releases.
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

        // List<Searchable> songs = new LinkedList<Searchable>();
        // songs.addAll(database.getSearchablesFromLibrary(args));
        // Collections.sort(songs, sort);
        
        System.out.println(songs);
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "(search type [song, release, artist]) []";
    }

    
}