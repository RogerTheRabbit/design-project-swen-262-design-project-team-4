package com.company.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchableMaker {
    private Database database;

    public SearchableMaker(Database database) {
        this.database = database;
    }

    public Searchable makeSearchable(String searchType, String[] fields){

        Searchable searchable = null;

        if(searchType.equalsIgnoreCase("Song")) {
            searchable = makeSong(fields);
        }
        else if(searchType.equalsIgnoreCase("Artist")){
            searchable = makeArtist(fields);
        }
        else if(searchType.equalsIgnoreCase("Release")){
            searchable = makeRelease(fields);
        }

        return searchable;
    }

    private Searchable makeSong(String[] fields){
        Searchable searchable = new Song(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
        return searchable;
    }

    private Searchable makeRelease(String[] fields) {
        try {
            ArrayList<Searchable> songs = new ArrayList<>();

            for (int i = 5; i < fields.length; i++) {
                Song song = database.getSong(fields[i]);
                songs.add(song);
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fields[3]);

            Medium medium = Medium.ERROR;
            if(fields[4].equalsIgnoreCase("CD")){
                medium = Medium.CD;
            }
            else if(fields[4].equalsIgnoreCase("Digital")){
                medium = Medium.Digital;
            }
            else{
                medium = Medium.Vinyl;
            }

            Searchable searchable = new Release(fields[0], fields[1], fields[2], date, medium, songs);
            return searchable;
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
    }

    private Searchable makeArtist(String[] fields){
        return new Artist(fields[0], fields[1], fields[2]);
    }
}
