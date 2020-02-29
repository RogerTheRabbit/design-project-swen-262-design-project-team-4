package com.company.Database;

import com.company.FileIO.FileSaver;

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
    private FileSaver FILEWRITER;
    private HashSet<Searchable> searchables;
    // TODO: Decide to keep using HashMap or switch to TreeSet
    // GUID , rating
    private HashMap<String, Integer> ratings;

    Library(Database database) {
        this.database = database;
        FILEWRITER = FileSaver.getInstance();
    }


    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        List<Searchable> songToAdd = database.getSearchable(searchableGUID).getSongList();

        if(songToAdd != null) {
            searchables.addAll(songToAdd);
            saveLibrary();
            return true;
        }
        return false;
    }

    public boolean removeSearchable(String searchableGUID) {
        List<Searchable> songToRemove = database.getSearchable(searchableGUID).getSongList();

        if(songToRemove != null) {
            searchables.removeAll(songToRemove);
            saveLibrary();
            return true;
        }
        return false;
    }

    boolean addRating(String searchableGUID, Integer rating) {
        saveLibrary();
        return false;
    }

    public void saveLibrary(){
        FILEWRITER.saveLibrary(username, searchables, ratings);
    }
}
