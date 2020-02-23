package com.company.Database;

import java.util.List;

public class Release implements Searchable {
    private String name;
    private String guid;
    private int duration;
    private Searchable artist;
    private List<Searchable> songList;

    public Release(String name, String guid, Searchable artist, List<Searchable> songList) {
        this.name = name;
        this.guid = guid;
        this.duration = calculateDuration(songList);
        this.artist = artist;
        this.songList = songList;
    }

    public int calculateDuration(List<Searchable> songList){
        int total = 0;
        for (Searchable currentSong : songList) {
            total += currentSong.getTotalDuration();
        }
        return total;
    }

    @Override
    public int getTotalDuration() {
        return duration;
    }

    @Override
    public List<Searchable> getSongList() {
        return songList;
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
