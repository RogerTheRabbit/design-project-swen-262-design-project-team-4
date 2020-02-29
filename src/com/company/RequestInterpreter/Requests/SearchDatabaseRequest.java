package com.company.RequestInterpreter.Requests;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.Filters.AlphabeticalArtist;

/**
 * SearchDatabaseRequest
 */
public class SearchDatabaseRequest implements Request {

    private Database database;

    private Comparator<Searchable> filter = new AlphabeticalArtist();

    public SearchDatabaseRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();
        songs.addAll(database.getSongs());
        Collections.sort(songs, filter);
        
        System.out.println(songs);
        return null;
    }

    @Override
    public String getUsageDesc() {
        return null;
    }

}
