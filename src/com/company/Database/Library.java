package com.company.Database;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Library
 * 
 */
public class Library {

    private String username = "Jimmy";
    private HashSet<Searchable> searchable;
    // TODO: Decide to keep using HashMap or switch to TreeSet
    //              GUID  , rating
    private HashMap<String, Integer> ratings;

    public HashSet<Searchable> getSearchable() {
        return searchable;
    }

    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        return false;

    }



}
