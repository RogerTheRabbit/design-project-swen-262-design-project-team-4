package com.company.Database;

import java.util.Date;
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
    public String getArtistGUID() {
        return artistGUID;
    }

    /**
     * gets the name of the release
     * @return name
     */
    @Override
    public String getName() {
        return title;
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
