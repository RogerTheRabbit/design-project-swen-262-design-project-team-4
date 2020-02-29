package com.company.Database;

import java.util.Date;
import java.util.List;

public class Release implements Searchable {
    private String title;
    private String guid;
    private int duration;
    private String artistGUID;
    private List<Searchable> songList;
    private Medium medium;
    private Date issueDate;


    public Release(String guid, String artistGUID, String title, Date issueDate, Medium medium, List<Searchable> songList) {
        this.title = title;
        this.guid = guid;
        this.duration = calculateDuration(songList);
        this.artistGUID = artistGUID;
        this.songList = songList;
        this.medium = medium;
        this.issueDate = issueDate;
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
        return title;
    }

    @Override
    public String getGUID() {
        return guid;
    }

    @Override
    public String toString() {
        return "Release{" +
                "title='" + title + '\'' +
                ", guid='" + guid + '\'' +
                ", duration=" + duration +
                ", artistGUID='" + artistGUID + '\'' +
                ", songList=" + songList +
                ", medium=" + medium +
                ", issueDate=" + issueDate +
                '}';
    }
}
