package com.company.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mjh9131
 *
 * a song containing all its identifying information and getters for it
 *
 * design pattern: Composite
 * role in pattern: Leaf
 */
public class Song implements Searchable {

    /**
     * Attributes
     */
    private String GUID;
    private String artistGUID;
    private int duration;
    private String title;
    private Integer rating;
    private Date acquisitionDate;

    /**
     *
     * Constructor
     *
     * @param GUID
     * @param artistGUID
     * @param duration
     * @param title
     */
    public Song(String GUID, String artistGUID, int duration, String title) {
        this.title = title;
        this.GUID = GUID;
        this.duration = duration;
        this.artistGUID = artistGUID;
        this.rating = 0;
        this.acquisitionDate = null;
    }

    /**
     * getter for the song's total duration
     * @return duration
     */
    @Override
    public int getTotalDuration() {
        return duration;
    }

    /**
     * puts the song in an ArrayList as the only entry
     * @return the song in an arraylist
     */
    @Override
    public List<Searchable> getSongList() {
        List<Searchable> song = new ArrayList<>();
        song.add(this);
        return song;
    }

    /**
     * gets the artist of the song
     * @return the artist
     */
    @Override
    public String getArtistGUID() {
        return artistGUID;
    }

    /**
     * gets the name of the song
     * @return name
     */
    @Override
    public String getName() {
        return title;
    }

    /**
     * gets the song's guid
     * @return guid
     */
    @Override
    public String getGUID() {
        return GUID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String formatToCsv(){
        return GUID  + "," + artistGUID + "," + duration + "," + title;
    }

    @Override
    public String toString() {
        return "Song{" +
                "GUID='" + GUID + '\'' +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                "}\n";
    }
}
