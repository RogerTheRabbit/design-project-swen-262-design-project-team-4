package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.FileIO.FileSaver;
import com.sun.java.accessibility.util.GUIInitializedListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Database {
    private FileParser FILEREADER;
    private Library Library;
    private HashMap<String, Song> songs;
    private HashMap<String, Release> releases;
    private HashMap<String, Artist> artists;

    public Database() {
        this.FILEREADER = new FileParser();
        Library = new Library(this);
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        initializeDatabase();
    }

    private void initializeDatabase() {
        SearchableMaker maker = new SearchableMaker(this);
        initializeArtists(maker);
        initializeSongs(maker);
        initializeAlbums(maker);
        // initializeLibrary();
    }

    private void initializeLibrary(String signedInUser) {

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

    private void AddSearchableToUserLibrary(String guid) {

    }

    private void addToArtistDiscography(Searchable entry) {
        String artistGUID = entry.getArtistGUID();
        Artist artist = artists.get(artistGUID);
        artist.addSearchable(entry);
    }

    public Song getSong(String GUID) {
        return songs.get(GUID);
    }

    public Collection<Song> getSongs() {
        return songs.values();
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
}
