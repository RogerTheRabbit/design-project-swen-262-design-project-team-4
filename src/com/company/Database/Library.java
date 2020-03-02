package com.company.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.company.FileIO.FileSaver;

import java.io.File;
import java.util.*;

/**
 * Library
 * 
 * This class represents a user's personal library.
 * 
 */
public class Library {

    private String username = "Jimmy";
    private Database database;
    private FileSaver FILEWRITER;
    private HashSet<Searchable> searchables;
    private HashMap<String, Collection<Searchable>> artistMap;

    Library(Database database) {
        this.database = database;
        FILEWRITER = FileSaver.getInstance();
        searchables = new HashSet<>();
        artistMap = new HashMap<>();
    }

    public Collection<Searchable> getSearchables(String searchableGUID) {
        return searchables;
    }

    /**
     * Gets a user's name.  This is for future implementations with multiple users.
     * 
     * @return the users username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Adds Searchables to the user's library based on their GUID.
     * 
     * @param searchableGUID the GUID of the searchable to add to the user's library
     * @return true if added successfully, false otherwise.
     */
    public boolean addSearchable(String searchableGUID) {

        Searchable songToAdd = database.getSearchable(searchableGUID);

        if(songToAdd != null) {
            addSongToArtistMap(songToAdd);
            searchables.add(songToAdd);
            return true;
        }
        return false;
    }

    /**
     * Helper function for addSearchable. This function adds a artist to the 
     * ArtistHashMap to keep track of what artists are in a user's database.
     * 
     * @param songToAdd Searchable to add to user's ArtistMap.
     */
    private void addSongToArtistMap(Searchable songToAdd){
        String artistguid = songToAdd.getArtistGUID();

        if(artistMap.containsKey(artistguid)){
            Collection<Searchable> songs = artistMap.get(artistguid);
            songs.add(songToAdd);
            artistMap.put(artistguid, songs);
        }
        else{
            Collection<Searchable> songs = new ArrayList<>();
            songs.add(songToAdd);
            artistMap.put(artistguid, songs);
        }
    }

    /**
     * Add an Acquisition date to a song.
     * Acquisition dates are added after a searchable is made.
     * 
     * @param guid GUID of searchable to add an acquisition date
     * @param accDate Date to add to Searchable
     */
    public void addAcquisitionDate(String guid, Date accDate){
        database.getSong(guid).setAcquisitionDate(accDate);
    }

    /**
     * Remove a searchable from a user's library and update the 
     * ArtistMap accordingly
     * 
     * @param searchableGUID GUID of searchable to remove from library
     * @return true of successful, false otherwise
     */
    public boolean removeSearchable(String searchableGUID) {
        Searchable songToRemove = database.getSearchable(searchableGUID);

        if(songToRemove != null) {

            removeSongFromArtistMap(songToRemove);

            searchables.remove(songToRemove);

            return true;
        }
        return false;
    }

    /**
     * Helper function for removeSearchable
     * Handles removing artists from the ArtistMap after removing a searchable
     * 
     * @param songToRemove Searchable song to remove from library
     */
    private void removeSongFromArtistMap(Searchable songToRemove){
        String key = songToRemove.getArtistGUID();

        Collection<Searchable> songs = artistMap.get(key);
        songs.remove(songToRemove);
        artistMap.put(key, songs);
        if(songs.size() == 0){
            artistMap.remove(key);
        }
    }

    /**
     * Add a rating to a song
     * 
     * @param searchableGUID Searchable GUID to add rating to
     * @param rating Rating to add to searchable.  Rating should be between 1 and 5 inclusive 
     */
    void addRating(String searchableGUID, Integer rating) {
        database.getSong(searchableGUID).setRating(rating);
    }

    /**
     * Given a list of searchables, make a new list that only contains the 
     * type of searchable.
     * 
     * @param searchableType The type of searchable to filter out
     * @return An ArrayList that contains only the searchables of Type searchableType
     */
    public ArrayList<Searchable> seperateSearchables(String searchableType){

        ArrayList<Searchable> seperatedSearchables = new ArrayList<>();

        if(searchableType.equalsIgnoreCase("Artist")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Artist){
                    seperatedSearchables.add(searchable);
                }
            }
        }
        else if(searchableType.equalsIgnoreCase("Release")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Release){
                    seperatedSearchables.add(searchable);
                }
            }
        }
        else if(searchableType.equalsIgnoreCase("Song")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Song){
                    seperatedSearchables.add(searchable);
                }
            }
        }

        return seperatedSearchables;
    }

    /**
     * Saves the library to a file to be read upon system restart.
     * 
     * This gets called before the system exits.
     */
    public void saveLibrary() {
        File artistFile = FILEWRITER.makeFile(username, "Artists");
        ArrayList<Searchable> artists = seperateSearchables("Artist");
        FILEWRITER.saveSearchables(artistFile, artists);

        File songsFile = FILEWRITER.makeFile(username, "Songs");
        ArrayList<Searchable> songs = seperateSearchables("Song");
        FILEWRITER.saveSearchables(songsFile, songs);

        File releasesFile = FILEWRITER.makeFile(username, "Releases");
        ArrayList<Searchable> releases = seperateSearchables("Release");
        FILEWRITER.saveSearchables(releasesFile, releases);


        File ratingFile = FILEWRITER.makeFile(username, "Ratings");
        FILEWRITER.saveHashmap(ratingFile, ratingsToHashMap(seperateSearchables("Song")));

        File acquisitionDateFile = FILEWRITER.makeFile(username, "Dates");
        Collection<Song> someSongs = new ArrayList<>();
        for(Searchable song: songs){
            someSongs.add((Song)song);
        }
        FILEWRITER.saveHashmap(acquisitionDateFile, datesToHashMap(someSongs));
    }

    /**
     * Takes a collection of Searchables and converts it into a hashmap.
     * key = GUID of Searchable
     * value = rating of song
     * 
     * @param searchables Searchables to convert into hashmap
     * @return Hashmap containing searchables in a key value pair where key = GUID of Searchable and value = rating of song
     */
    public HashMap<String, Integer> ratingsToHashMap(Collection<Searchable> searchables){
        HashMap<String, Integer> ratingsMap = new HashMap<>();
        for(Searchable song: searchables){
            ratingsMap.put(song.getGUID(), song.getRating());
        }
        return ratingsMap;
    }

    /**
     * Converts dates to a hashmap.
     * key = Searchable GUID
     * value = rating
     * 
     * @param searchables Collection of searchables to add to convert to map
     * @return Hashmap containing searchables in a key value pair where key = Searchable GUID and value = rating
     */
    public HashMap<String, Date> datesToHashMap(Collection<Song> searchables) {
        HashMap<String, Date> ratingsMap = new HashMap<>();
        for(Song song: searchables){
            ratingsMap.put(song.getGUID(), song.getAcquisitionDate());
        }
        return ratingsMap;
    }
}
