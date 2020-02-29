package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.FileIO.FileSaver;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private FileSaver FILEWRITER;
    private FileParser FILEREADER;
    private Library Library;
    private HashMap<String, Song> songs;
    private HashMap<String, Release> releases;
    private HashMap<String, Artist> artists;

    public Database() {
        this.FILEWRITER = new FileSaver();
        this.FILEREADER = new FileParser();
        Library = new Library();
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        initializeDatabase();
    }

    private void initializeDatabase(){
        SearchableMaker maker = new SearchableMaker(this);
        initializeArtists(maker);
        initializeSongs(maker);
        initializeAlbums(maker);
        //initializeLibrary();
    }

    private void initializeLibrary(String signedInUser){

    }

    private void initializeSongs(SearchableMaker maker){
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for(String[] fields: splitData){
            Searchable entry = maker.makeSearchable("Song", fields);
            songs.put(entry.getGUID(), (Song)entry);
        }
    }

    private void initializeArtists(SearchableMaker maker){
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
        for(String[] fields: splitData){
            Searchable entry = maker.makeSearchable("Artist", fields);
            artists.put(entry.getGUID(), (Artist)entry);
        }
    }

    private void initializeAlbums(SearchableMaker maker){
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
    }

    private void AddSearchableToUserLibrary(String guid){

    }
}
