package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.RequestInterpreter.Filters.DateRangeFilter;
import com.company.RequestInterpreter.Filters.Filter;
import com.company.RequestInterpreter.Filters.GUIDFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mjh9131
 *
 * contains all the songs, releases, and artists on record
 */
public class Database {

    /**
     * Attributes
     */
    private FileParser FILEREADER;
    private Library library;
    private HashMap<String, Song> songs;
    private HashMap<String, Release> releases;
    private HashMap<String, Artist> artists;

    /**
     * Constructor
     */
    public Database() {
        this.FILEREADER = new FileParser();
        library = new Library(this);
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        initializeDatabase();
    }

    /**
     * saves the library's contents
     */
    public void saveLibrary(){
        library.saveLibrary();
    }

    /**
     * ===================================================================================
     *                              initializers
     * ===================================================================================
     */

    /**
     * initializes the database by:
     *
     * reading the csv files
     * constructing searchables from the data in the files
     * adding those searchables to the database
     * then it constructs the user's library
     */
    private void initializeDatabase() {
        SearchableMaker maker = new SearchableMaker(this);
        initializeArtists(maker);
        initializeSongs(maker);
        initializeAlbums(maker);
        initializeLibrary(library.getUsername());
    }

    /**
     * builds the library by adding each type of searchable to it from their coresponding files
     * adds
     * @param signedInUser
     */
    private void initializeLibrary(String signedInUser) {
        addSearchableToLibraryFromFile(signedInUser, "Artists");
        addSearchableToLibraryFromFile(signedInUser, "Songs");
        addSearchableToLibraryFromFile(signedInUser, "Releases");
        addRatingToSongFromFile(signedInUser);
        addAcquisitionDateFromFile(signedInUser);
    }


    private void initializeSongs(SearchableMaker maker) {
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        try{
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Song", fields);
                songs.put(entry.getGUID(), (Song) entry);
                addToArtistDiscography(entry);
            }
        }
        catch (java.io.IOException e){
            System.err.println(e);
        }
    }

    private void initializeArtists(SearchableMaker maker) {
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Artist", fields);
                artists.put(entry.getGUID(), (Artist) entry);
            }
        }
        catch (java.io.IOException e){
            System.err.println(e);
        }
    }

    private void initializeAlbums(SearchableMaker maker) {
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Release", fields);
                releases.put(entry.getGUID(), (Release) entry);
                addToArtistDiscography(entry);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    /**
     * ===================================================================================
     *                              getters for items in the database
     * ===================================================================================
     */

    private void addSearchableToLibraryFromFile(String signedInUser, String searchableType){
        FILEREADER.setFileName(signedInUser + searchableType + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                library.addSearchable(fields[0]);
            }
        }
        catch (java.io.IOException e){
            //System.err.println(e);
        }
    }

    private void addRatingToSongFromFile(String signedInUser){
        FILEREADER.setFileName(signedInUser + "Ratings" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song songToAddRating = songs.get(fields[0]);
                songToAddRating.setRating(Integer.parseInt(fields[1]));
            }
        }
        catch (java.io.IOException e){
            //System.err.println(e);
        }
    }

    private void addAcquisitionDateFromFile(String signedInUser){
        FILEREADER.setFileName(signedInUser + "Dates" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song songToAddDate = songs.get(fields[0]);
                songToAddDate.setAcquisitionDate(SearchableMaker.makeDate(fields[1]));
            }
        }
        catch (Exception e){
            //System.err.println(e);
        }
    }

    private void addToArtistDiscography(Searchable entry) {
        String artistGUID = entry.getArtistGUID();
        Artist artist = artists.get(artistGUID);
        artist.addSearchable(entry);
    }

    /**
     * ===================================================================================
     *                              getters for items in the database
     * ===================================================================================
     */

    public Collection<Searchable> getMusic() {
        Collection<Searchable> music = new LinkedList<Searchable>();
        music.addAll(songs.values());
        music.addAll(releases.values());
        return music;
    }

    public Song getSong(String GUID) {
        return songs.get(GUID);
    }

    public Artist getArtist(String GUID){
        return artists.get(GUID);
    }

    public Release getRelease(String GUID){
        return releases.get(GUID);
    }

    /**
     * Given a string of a guid, will search the database and
     * retrieve a searchable of that guid if it exists
     *
     * @param GUID the guid of the searchable to be added to the database
     * @return the searchable of
     */
    public Searchable getSearchable(String GUID) {
        if(getSong(GUID) != null) {
            return getSong(GUID);
        } else if (getArtist(GUID) != null) {
            return getArtist(GUID);
        } else if (getRelease(GUID) != null) {
            return getRelease(GUID);
        }
        return null;
    }

    public Collection<Searchable> getSearchablesFromLibrary(String searchableGUID) {
        return library.getSearchables(searchableGUID);
    }

	public void addSearchableToLibrary(String searchableGUID, Date aquDate) {
        library.addAcquisitionDate(searchableGUID, aquDate);
        library.addSearchable(searchableGUID);
    }

	public void removeSearchableFromLibrary(String searchableGUID) {
        library.removeSearchable(searchableGUID);
    }
    
    public void rateSearchableInLibrary(String searchableGUID, int rating) {
        library.addRating(searchableGUID, rating);
    }

    private static final HashMap<String, Filter> FILTERS;
    static {
        FILTERS = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        FILTERS.put("name", new DateRangeFilter());
        FILTERS.put("artist", new DateRangeFilter());
        FILTERS.put("duration", new DateRangeFilter());
        FILTERS.put("GUID", new GUIDFilter());
        FILTERS.put("date-range", new DateRangeFilter());
    }

	public Collection<Song> getSongs(String searchFilter, String searchValue) {
        
        if(FILTERS.containsKey(searchFilter)) {
            return FILTERS.get(searchFilter).filterSongs(songs.values(), searchValue);
        } else {
            System.err.printf("Invalid search filter '%s' for songs\n", searchFilter);
            return new LinkedList<Song>();
        }
	}

	public Collection<Release> getReleases(String searchFilter, String searchValue) {
        if(FILTERS.containsKey(searchFilter)) {
            return FILTERS.get(searchFilter).filterReleases(releases.values(), searchValue);
        } else {
            System.err.printf("Invalid search filter '%s' for releases\n", searchFilter);
            return new LinkedList<Release>();
        }
	}

}
