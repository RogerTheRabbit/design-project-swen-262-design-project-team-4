package com.company.Database;

import java.util.List;

public class Release implements Searchable {
    private String name;
    private String guid;
    private int duration;
    private String artistGUID;
    private List<Searchable> songList;

    public Release( String guid, String artistGUID, String name,String issueDate, Enum medium,  List<String> songGUIDList) {
        this.name = name;
        this.guid = guid;
        this.duration = calculateDuration(songList);
        this.artistGUID = artistGUID;
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
    public String getArtistGUID() {
        return artistGUID;
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
