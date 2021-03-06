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
    private HashMap<String, ArrayList<Searchable>> artistMap;

    Library(Database database) {
        this.database = database;
        FILEWRITER = FileSaver.getInstance();
        searchables = new HashSet<>();
        artistMap = new HashMap<>();
    }

    /**
     * Returns a hashmap of all the artists in a user's Library
     * where key = GUID of artist and value = Collection of Searchables of all 
     * songs in user's Library from the respective  
     * 
     * @return the hashmap of artists and their respective searchables in the user's Library.
     */
    public HashMap<String, ArrayList<Searchable>> getArtistMap() {
        return artistMap;
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
        } else {
            System.err.println("Failed to add to your personal library.");
            return false;
        }
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
            ArrayList<Searchable> songs = artistMap.get(artistguid);
            songs.add(songToAdd);
            artistMap.put(artistguid, songs);
        }
        else{
            ArrayList<Searchable> songs = new ArrayList<>();
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
        Searchable searchable = database.getSearchable(guid);
        if(searchable != null) {
            searchable.setAcquisitionDate(accDate);
        } else {
            System.err.printf("Can not find song or artist with GUID %s\n", guid);
        }
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

        ArrayList<Searchable> songs = artistMap.get(key);
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
     * @param rating         Rating to add to searchable. Rating should be between 1
     *                       and 5 inclusive
     * @throws Exception
     */
    void addRating(String searchableGUID, Integer rating) throws Exception {
        Song song = database.getSong(searchableGUID);
        if (song == null) {
            System.err.printf("You can only rate songs. Song with GUID of '%s' not found.\n", searchableGUID);
            throw new Exception();
        }
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
     * This should get called before the system exits.
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
        Collection<Searchable> someSongs = new ArrayList<>(releases);
        someSongs.addAll(songs);
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
    public HashMap<String, Date> datesToHashMap(Collection<Searchable> searchables) {
        HashMap<String, Date> ratingsMap = new HashMap<>();
        for(Searchable searchable: searchables){
            ratingsMap.put(searchable.getGUID(), searchable.getAcquisitionDate());
        }
        return ratingsMap;
    }

    /**
     * Returns the artist with the given GUID
     * 
     * @param GUID GUID of artist to get
     * @return Artist with GUID. Null if artist not in user's library.
     */
	public Artist getArtist(String GUID) {
        System.out.println(GUID);
        System.out.println(searchables);
        for(Searchable artist : searchables) {
            if(artist.getGUID().equals(GUID)) {
                return (Artist) artist;
            }
        }
		return null;
	}

    /**
     * Returns the release with the given GUID
     * 
     * @param GUID GUID of release to get
     * @return Release with GUID. Null if release not in user's library.
     */
	public Release getRelease(String GUID) {
        for(Searchable artist : searchables) {
            if(artist.getGUID().equals(GUID)) {
                return (Release) artist;
            }
        }
		return null;
	}

    /**
     * Returns the song with the given GUID
     * 
     * @param GUID GUID of song to get
     * @return Song with GUID. Null if song not in user's library.
     */
	public Song getSong(String GUID) {
        for(Searchable artist : searchables) {
            if(artist.getGUID().equals(GUID)) {
                return (Song) artist;
            }
        }
		return null;
	}
}
