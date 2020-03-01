package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.FileIO.FileSaver;
import com.sun.java.accessibility.util.GUIInitializedListener;

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

    public Database() {
        this.FILEREADER = new FileParser();
        library = new Library(this);
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        initializeDatabase();
    }

    public void saveLibrary(){
        library.saveLibrary();
    }

    private void initializeDatabase() {
        SearchableMaker maker = new SearchableMaker(this);
        initializeArtists(maker);
        initializeSongs(maker);
        initializeAlbums(maker);
        // initializeLibrary();
    }

    private void initializeLibrary(String signedInUser) {
        addSearchableToLibraryFromFile(signedInUser, "Artists");
        addSearchableToLibraryFromFile(signedInUser, "Songs");
        addSearchableToLibraryFromFile(signedInUser, "Releases");
        addRatingToLibraryFromFile(signedInUser);
    }

    private void addSearchableToLibraryFromFile(String signedInUser, String searchableType){
        FILEREADER.setFileName(signedInUser + searchableType + ".csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for (String[] fields : splitData) {
            library.addSearchable(fields[0]);
        }
    }

    private void addRatingToLibraryFromFile(String signedInUser){
        FILEREADER.setFileName(signedInUser + "Ratings" + ".csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for (String[] fields : splitData) {
            library.addRating(fields[0], Integer.parseInt(fields[1]));
        }
    }

    private void initializeSongs(SearchableMaker maker) {
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for (String[] fields : splitData) {
            Searchable entry = maker.makeSearchable("Song", fields);
            songs.put(entry.getGUID(), (Song) entry);
            addToArtistDiscography(entry);
        }
    }

    private void initializeArtists(SearchableMaker maker) {
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for (String[] fields : splitData) {
            Searchable entry = maker.makeSearchable("Artist", fields);
            artists.put(entry.getGUID(), (Artist) entry);
        }
    }

    private void initializeAlbums(SearchableMaker maker) {
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for (String[] fields : splitData) {
            Searchable entry = maker.makeSearchable("Release", fields);
            releases.put(entry.getGUID(), (Release) entry);
            addToArtistDiscography(entry);
        }
    }

    private void addToArtistDiscography(Searchable entry) {
        String artistGUID = entry.getArtistGUID();
        Artist artist = artists.get(artistGUID);
        artist.addSearchable(entry);
    }

    public Song getSong(String GUID) {
        return songs.get(GUID);
    }

    public Collection<Searchable> getMusic() {
        Collection<Searchable> music = new LinkedList<Searchable>();
        music.addAll(songs.values());
        music.addAll(releases.values());
        return music;
    }

    public Artist getArtist(String GUID){
        return artists.get(GUID);
    }

    public Release getRelease(String GUID){
        return releases.get(GUID);
    }

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

    public Collection<Searchable> getSearchablesFromLibrary(String searchableName) {
        return library.getSearchables(searchableName);
    }

	public void addSearchableToLibrary(String GUID, Date date) {
        library.addSearchable(searchableGUID)
	}
}
