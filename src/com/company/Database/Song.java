package com.company.Database;

import java.util.ArrayList;
import java.util.List;

public class Song implements Searchable {
    private String name;
    private String guid;
    private int duration;
    private Searchable artist;

    public Song(String name, String guid, int duration, Searchable artist) {
        this.name = name;
        this.guid = guid;
        this.duration = duration;
        this.artist = artist;
    }

    @Override
    public int getTotalDuration() {
        return duration;
    }

    @Override
    public List<Searchable> getSongList() {
        List<Searchable> song = new ArrayList<>();
        song.add(this);
        return song;
    }

    @Override
    public Searchable getArtist() {
        return artist;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGUID() {
        return guid;
    }
}
