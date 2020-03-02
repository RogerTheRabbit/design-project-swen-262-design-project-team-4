package com.company.RequestInterpreter.Requests;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.Filters.Filter;
import com.company.RequestInterpreter.Sorts.AlphabeticalArtist;

/**
 * SearchDatabaseRequest
 */
public class SearchDatabaseRequest implements Request {

    private Database database;

    private Comparator<Searchable> sort = new AlphabeticalArtist();
    private Filter filter;

    public void setSort(Comparator<Searchable> sort) {
        this.sort = sort;
    }

    public void setFilter(String filter) {
        // Handle setting filter.
    }

    public SearchDatabaseRequest(Database database) {
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
                songs.addAll(database.getSongs(searchValue.trim()));
                break;
            case "release":
                songs.addAll(database.getReleases(searchValue.trim()));
                break;
            default:
                System.err.println("Invalid search type. Please specify either 'song' or 'release'.");
                return null;
        }

        // Sort results by alphabetical order
        // This is not required for searching the database, but why not do it anyways
        Collections.sort(songs, sort);
        
        System.out.println(songs);
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "[search type (song, release)] [search value]";
    }

}
