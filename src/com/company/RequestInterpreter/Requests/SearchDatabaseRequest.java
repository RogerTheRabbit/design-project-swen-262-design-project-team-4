package com.company.RequestInterpreter.Requests;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.Sorts.AlphabeticalArtist;

/**
 * SearchDatabaseRequest
 */
public class SearchDatabaseRequest implements Request {

    private Database database;

    private Comparator<Searchable> sort = new AlphabeticalArtist();

    public void setSort(Comparator<Searchable> sort) {
        this.sort = sort;
    }

    public SearchDatabaseRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();

        String[] params = args.split(" ");

        if(params.length < 3) {
            System.err.println("Invalid number of params.  Must use 3 params: [search type (song, release)] [search filter (title, name, artists, duration, GUID, date-range)] [search value]");
            return null;
        }

        String searchType = params[0];
        String searchFilter = params[1];
        // Rest of string is song
        String searchValue = args.substring(params[0].length() + params[1].length() + 2);

        // Get data
        switch (searchType) {
            case "song":
                songs.addAll(database.getSongs(searchFilter, searchValue));
                break;
            case "release":
                songs.addAll(database.getReleases(searchFilter, searchValue));
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
        return "[search type (song, release)] [search filter (name, artists, duration, GUID, date-range)] [search value]";
    }

}
