package com.company.Database;

import java.util.ArrayList;

public class SearchableMaker {
    public SearchableMaker() {
    }

    public Searchable makeSearchable(String searchType, String[] fields){
        switch (searchType) {
            case "song":
                makeSong(fields);
                break;

            case "artist":
                makeArtist(fields);
                break;

            case "release":
                makeRelease(fields);
                break;
        }
        System.err.println("INVALID SEARCHABLE TYPE");
        return null;
    }

    private Searchable makeSong(String[] fields){
        Searchable searchable = new Song(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
        return searchable;
    }

    private Searchable makeRelease(String[] fields){
        ArrayList<String> songs = new ArrayList<>();
        for(int i = 4; i < fields.length; i++){
            songs.add(fields[i]);
        }

        return null;
        //TODO return new Release(fields[0], fields[1], fields[2], fields[3], , songs);
    }

    private Searchable makeArtist(String[] fields){
        return null;
    }
}
