package com.company.Database;

import java.util.ArrayList;
import java.util.List;

public class Song implements Searchable {
    private String name;
    private String GUID;
    private int duration;
    private String artistGUID;

    public Song(String GUID, String artistGUID, int duration, String title) {
        this.name = title;
        this.GUID = GUID;
        this.duration = duration;
        this.artistGUID = artistGUID;
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
    public String getArtistGUID() {
        return artistGUID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGUID() {
        return GUID;
    }
}
