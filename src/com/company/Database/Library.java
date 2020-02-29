package com.company.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

    public Collection<Searchable> getSearchable() {
        return searchables;
    }

    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        List<Searchable> songToAdd = database.getSearchable(searchableGUID).getSongList();

        if(songToAdd != null) {
            for(Searchable searchableToAdd : songToAdd) {
                searchables.add(searchableToAdd);
            }
            return true;
        }
        return false;
    }

    public boolean removeSearchables(String searchableGUID) {
        List<Searchable> songToRemove = database.getSearchable(searchableGUID).getSongList();

        if(songToRemove != null) {
            for(Searchable searchableToAdd : songToRemove) {
                searchables.remove(searchableToAdd);
            }
            return true;
        }
        return false;
    }

    boolean addRating(String searchableGUID, Integer rating) {
        // TODO: Figure out how ratings are going to be stored first.
        return false;
    }
}
