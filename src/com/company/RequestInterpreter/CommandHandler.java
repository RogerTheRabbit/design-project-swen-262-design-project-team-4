package com.company.RequestInterpreter;

import com.company.Database.Database;
import com.company.Database.Library;
import com.company.ResponseFormatter.ResponseFormatter;

import java.util.Date;
import java.util.HashMap;

public class CommandHandler {
    private ResponseFormatter responseFormatter;
    private Database database;
    private HashMap<String, Library> libraries;

    public void saveLibrary(String username){
        libraries.get(username).saveLibrary();
    }

    /**
     * given a searchable guid and acquisition date, will add the searchable to the user's library
     * @param searchableGUID the guid of the searchable to add
     * @param aquDate the acquisition date of the searchable to add
     */
    public void addSearchableToLibrary(String searchableGUID, Date aquDate) {
        Library library = libraries.get(username);
        if(library.getArtist(searchableGUID) != null) {
            System.out.println("Artist not added to library.  Only songs and releases can be added to your library.");
            return;
        }
        library.addAcquisitionDate(library.getSong(), aquDate);
        library.addSearchable(searchableGUID, );
    }

    /**
     * given a searchable guid, remove it from a user's library
     * @param searchableGUID the guid of the searchable to be removed
     */
    public boolean removeSearchableFromLibrary(String searchableGUID) {
        return library.removeSearchable(searchableGUID);
    }

    /**
     * rates a searchable in a user's library
     *
     * @param searchableGUID the guid of the searchable to be rated
     * @param rating         the rating to be set
     * @throws Exception
     */
    public void rateSearchableInLibrary(String searchableGUID, int rating) throws Exception {
        library.addRating(searchableGUID, rating);
    }

}
