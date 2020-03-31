package com.company.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.company.FileIO.FileParser;
import com.company.FileIO.FileSaver;
import com.company.RequestInterpreter.Response;
import com.company.SearchableFactory.ArtistFactory;
import com.company.SearchableFactory.DateMaker;
import com.company.SearchableFactory.ReleaseFactory;
import com.company.SearchableFactory.SongFactory;

import java.io.File;
import java.util.*;

/**
 * Library
 * 
 * This class represents a user's personal library.
 * 
 */
public class Library implements Database{

    private FileParser FILEREADER;
    private String username;
    private FileSaver FILEWRITER;
    private HashSet<Searchable> searchables;
    private HashMap<Artist, ArrayList<Searchable>> artistMap;

    public Library(String username) {
        this.username = username;
        this.FILEREADER = new FileParser();
        FILEWRITER = FileSaver.getInstance();
        searchables = new HashSet<>();
        artistMap = new HashMap<>();
        initializeLibrary(username);
    }

    /**
     * Returns a hashmap of all the artists in a user's Library
     * where key = GUID of artist and value = Collection of Searchables of all 
     * songs in user's Library from the respective  
     * 
     * @return the hashmap of artists and their respective searchables in the user's Library.
     */
    public HashMap<Artist, ArrayList<Searchable>> getArtistMap() {
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
     * @param songToAdd the searchable to add to the user's library
     * @return true if added successfully, false otherwise.
     */
    public boolean addSearchable(Searchable songToAdd, Artist artist) {

        if(songToAdd != null) {
            addSongToArtistMap(songToAdd, artist);
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
    private void addSongToArtistMap(Searchable songToAdd, Artist songArtist){

        if(artistMap.containsKey(songArtist)){
            ArrayList<Searchable> songs = artistMap.get(songArtist);
            songs.add(songToAdd);
            artistMap.put(songArtist, songs);
        }
        else{
            ArrayList<Searchable> songs = new ArrayList<>();
            songs.add(songToAdd);
            artistMap.put(songArtist, songs);
        }
    }

    private void addArtistToArtistMapFromFile(Artist artist){
        ArrayList<Searchable> songs = new ArrayList<>();
        artistMap.put(artist, songs);
    }

    /**
     * Add an Acquisition date to a song.
     * Acquisition dates are added after a searchable is made.
     * 
     * @param searchable searchable to add an acquisition date
     * @param accDate Date to add to Searchable
     */
    public void addAcquisitionDate(Searchable searchable, Date accDate){
        if(searchable != null) {
            searchable.setAcquisitionDate(accDate);
        } else {
            System.err.printf("Can not find song or artist with GUID %s\n", searchable.getGUID());
        }
    }

    /**
     * Remove a searchable from a user's library and update the 
     * ArtistMap accordingly
     * 
     * @param songToRemove searchable to remove from library
     * @return true of successful, false otherwise
     */
    public boolean removeSearchable(Searchable songToRemove, Artist songArtist) {

        if(songToRemove != null) {

            removeSongFromArtistMap(songToRemove, songArtist);

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
    private void removeSongFromArtistMap(Searchable songToRemove, Artist songArtist){

        ArrayList<Searchable> songs = artistMap.get(songArtist);
        songs.remove(songToRemove);
        artistMap.put(songArtist, songs);
        if(songs.size() == 0){
            artistMap.remove(songArtist);
        }
    }

    /**
     * Add a rating to a song
     * 
     * @param song Searchable to add rating to
     * @param rating         Rating to add to searchable. Rating should be between 1
     *                       and 5 inclusive
     * @throws Exception
     */
    void addRating(Song song, Integer rating) throws Exception {
        if (song == null) {
            System.err.printf("You can only rate songs. Song with GUID of '%s' not found.\n", song.getGUID());
            throw new Exception();
        }
        song.setRating(rating);
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
    @Override
	public Artist getArtist(String GUID) {
        for(Artist artist : artistMap.keySet()) {
            if(artist.getGUID().equals(GUID)) {
                return artist;
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
    @Override
	public Release getRelease(String GUID) {
        for(Searchable release : searchables) {
            if(release.getGUID().equals(GUID)) {
                return (Release) release;
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
    @Override
	public Song getSong(String GUID) {
        for(Searchable song : searchables) {
            if(song.getGUID().equals(GUID)) {
                return (Song) song;
            }
        }
		return null;
	}


    @Override
    public Response getSongs() {
        return new Response(seperateSearchables("Song"), true, "List of all Songs");
    }

    @Override
    public Response getReleases() {
        return new Response(seperateSearchables("Release"), true, "List of all Releases");
    }

    @Override
    public Response getArtists() {
        return new Response(seperateSearchables("Artist"), true, "List of all Artists");
    }


    /**
     * builds the library by adding each type of searchable to it from their
     * coresponding files adds
     *
     * @param signedInUser
     */
    private void initializeLibrary(String signedInUser) {
        addArtistToLibraryFromFile(signedInUser);
        addSongsToLibraryFromFile(signedInUser);
        addReleasesToLibraryFromFile(signedInUser);
        addRatingToSongFromFile(signedInUser);
        addAcquisitionDateFromFile(signedInUser);
    }

    /**
     * reads the user's associated searchable files and adds them to their library
     * @param signedInUser      the user's username
     */
    private void addArtistToLibraryFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Artist.csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArtistFactory maker = new ArtistFactory();
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                addArtistToArtistMapFromFile(maker.makeArtistFromCsv(fields));
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }
    /**
     * reads the user's associated searchable files and adds them to their library
     * @param signedInUser      the user's username
     */
    private void addSongsToLibraryFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Songs.csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            SongFactory maker = new SongFactory();
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song songToAdd = maker.makeSongFromCsv(fields);
                addSearchable(songToAdd, getArtist(songToAdd.getGUID()));
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }
    /**
     * reads the user's associated searchable files and adds them to their library
     * @param signedInUser      the user's username
     */
    private void addReleasesToLibraryFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Releases.csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ReleaseFactory maker = new ReleaseFactory();
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Release releaseToAdd = maker.makeReleaseFromCsv(fields, this);
                addSearchable(releaseToAdd, getArtist(releaseToAdd.getGUID()));
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }


    /**
     * reads the user's associated rating files and adds them to the corresponding searchable
     * @param signedInUser the user's username
     */
    private void addRatingToSongFromFile(String signedInUser){
        FILEREADER.setFileName(signedInUser + "Ratings" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song songToAddRating = getSong(fields[0]);
                songToAddRating.setRating(Integer.parseInt(fields[1]));
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }

    /**
     * reads the user's associated acquisition date files and adds them to the corresponding searchable
     * @param signedInUser the user's username
     */
    private void addAcquisitionDateFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Dates" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            DateMaker maker = new DateMaker();
            for (String[] fields : splitData) {
                Searchable songToAddDate = getSong(fields[0]);
                Searchable releaseToAddDate = getRelease(fields[0]);
                if(songToAddDate != null){
                    songToAddDate.setAcquisitionDate(maker.makeDate(fields[1]));
                }
                if(releaseToAddDate != null){
                    releaseToAddDate.setAcquisitionDate(maker.makeDate(fields[1]));

                }
            }
        } catch (Exception e) {
            // System.err.println(e);
        }
    }
}
