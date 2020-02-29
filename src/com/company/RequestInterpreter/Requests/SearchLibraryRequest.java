package com.company.RequestInterpreter.Requests;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Database;
import com.company.Database.Searchable;
import com.company.RequestInterpreter.Filters.AlphabeticalArtist;

/**
 * SearchLibraryRequest
 */
public class SearchLibraryRequest implements Request {

    Database database;

    public SearchLibraryRequest(Database database) {
        this.database = database;
    }

    private Comparator<Searchable> filter = new AlphabeticalArtist();

    public void setFilter(Comparator<Searchable> filter) {
        this.filter = filter;
    }

    // TODO: Make it so this doesn't show artists.  Currently shows all types of searchables but should only show songs and releases.
    @Override
    public Response handle(String args) {

        List<Searchable> songs = new LinkedList<Searchable>();
        songs.addAll(database.getSearchablesFromLibrary());
        Collections.sort(songs, filter);
        
        System.out.println(songs);
        return null;
    }

    @Override
    public String getUsageDesc() {
        return null;
    }

    
}