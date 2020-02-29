package com.company.Database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Library
 * 
 */
public class Library {

    private String username = "Jimmy";
    private Database database;
    private HashSet<Searchable> searchables;
    // TODO: Decide to keep using HashMap or switch to TreeSet
    // GUID , rating
    private HashMap<String, Integer> ratings;

    Library(Database database) {
        this.database = database;
    }

    public HashSet<Searchable> getSearchable() {
        return searchables;
    }

    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        List<Searchable> songToAdd = database.getSearchable(searchableGUID).getSongList();

        if(songToAdd != null) {
            // TODO: Add songs here
            return true;
        }
        return false;
    }
}
