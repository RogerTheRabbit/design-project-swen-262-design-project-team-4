package com.company.RequestInterpreter;

import com.company.Database.Database;
import com.company.Database.Library;
import com.company.ResponseFormatter.ResponseFormatter;

import java.util.Date;
import java.util.HashMap;

public class CommandHandler {
    private Database database;
    private HashMap<String, Library> libraries;
    private HashMap<String, ResponseFormatter> formatters;

    public CommandHandler() {

    }

    public void initializeUser(String username){
        libraries.put(username, new Library(username));
        formatters.put(username, new ResponseFormatter());
    }

    public void saveLibrary(String username){
        libraries.get(username).saveLibrary();
    }

    /**
     * given a searchable guid and acquisition date, will add the searchable to the user's library
     * @param searchableGUID the guid of the searchable to add
     * @param aquDate the acquisition date of the searchable to add
     */
    public Response addSearchableToLibrary(String searchableGUID, Date aquDate, String username) {
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
    public Response removeSearchableFromLibrary(String searchableGUID, String username) {
        return library.removeSearchable(searchableGUID);
    }

    /**
     * rates a searchable in a user's library
     *
     * @param searchableGUID the guid of the searchable to be rated
     * @param rating         the rating to be set
     * @throws Exception
     */
    public Response rateSearchableInLibrary(String searchableGUID, int rating, String username) throws Exception {
        library.addRating(searchableGUID, rating);
    }

    public Response browseArtist(String[] params){
        return null;
    }

    public Response getSongFromLibrary(String[] params){
        return null;
    }

    public Response getReleaseFromLibrary(String[] params){
        return null;
    }

    public Response getArtistFromLibrary(String[] params){
        return null;
    }

    public Response getSongsFromLibrary(String[] params){
        return null;
    }

    public Response getReleasesFromLibrary(String[] params){
        return null;
    }

    public Response getArtistsFromLibrary(String[] params){
        return null;
    }

    public Response setFilter(String[] params){
        return null;
    }

    public Response setSort(String[] params){
        return null;
    }
}
