package com.company.Database;

import java.util.List;

/**
 * @author mjh9131
 *
 * a release is a collection of songs with identifying information
 *
 * design pattern: Composite
 * role in pattern: Composite
 */
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

    /**
     * iterates through a list of songs and calculates the total duration of all the songs combined
     * @param songList the list of songs in the release
     * @return the duration of all the songs in the songlist
     */
    public int calculateDuration(List<Searchable> songList){
        int total = 0;
        for (Searchable currentSong : songList) {
            total += currentSong.getTotalDuration();
        }
        return total;
    }

    /**
     * gets the total duration
     * @return duration of the release
     */
    @Override
    public int getTotalDuration() {
        return duration;
    }

    /**
     * gets the list of songs in the release
     * @return the song list
     */
    @Override
    public List<Searchable> getSongList() {
        return songList;
    }

    /**
     * gets the artist of the release
     * @return artist
     */
    @Override
    public Searchable getArtist() {
        return artist;
    }

    /**
     * gets the name of the release
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the GUID of the release
     * @return guid
     */
    @Override
    public String getGUID() {
        return guid;
    }
}
