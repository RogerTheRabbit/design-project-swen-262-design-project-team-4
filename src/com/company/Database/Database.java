package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.FileIO.FileSaver;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private FileSaver FILEWRITER;
    private FileParser FILEREADER;
    private Library Library;
    private HashMap<String, Searchable> searchables;

    public Database() {
        this.FILEWRITER = new FileSaver();
        this.FILEREADER = new FileParser();
        Library = new Library();
        this.searchables = new HashMap<>();
    }

    private void initializeDatabase(){
        initializeArtists();
        initializeSongs();
        initializeAlbums();
        //initializeLibrary();
    }

    private void initializeLibrary(String signedInUser){

    }

    private void initializeSongs(){
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();

    }

    private void initializeArtists(){
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
    }

    private void initializeAlbums(){
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        ArrayList<String[]> splitData = FILEREADER.readFile();
    }

    private void AddSearchableToUserLibrary(String guid){

    }
}
