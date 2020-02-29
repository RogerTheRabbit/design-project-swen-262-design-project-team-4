package com.company.Database;

import java.util.ArrayList;
import java.util.List;

public class Artist implements Searchable {
    private String name;
    private String guid;
    private String disambiguation;
    private int duration;
    private List<Searchable> discography;

    public Artist(String guid, String name, String disambiguation) {
        this.name = name;
        this.guid = guid;
        this.disambiguation = disambiguation;
        this.duration = calculateDuration(discography);
        this.discography = new ArrayList<>();
    }

    public int calculateDuration(List<Searchable> discography){
        int total = 0;
        for (Searchable currentSearchable : discography) {
            total += currentSearchable.getTotalDuration();
        }
        return total;
    }

    public void addSearchable(Searchable searchable){
        discography.add(searchable);
        duration = calculateDuration(discography);
    }

    @Override
    public int getTotalDuration() {
        return duration;
    }

    @Override
    public List<Searchable> getSongList() {
        List<Searchable> songs = new ArrayList<>();
        for(Searchable searchable: discography){
            songs.addAll(searchable.getSongList());
        }
        return songs;
    }

    @Override
    public String getArtistGUID() {
        return guid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGUID() {
        return guid;
    }

    //TODO: How to handle this because songs and releases do not have disambiguations
    public String getDisambiguation() {
        return disambiguation;
    }
}
