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

    /**
     * Gets the rating
     * @return rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the rating
     * @param rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets the acquisition date
     * @return acquisition date
     */
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date
     * @param acquisitionDate
     */
    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Formatter for converting to csv files
     * @return the formatted string
     */
    public String formatToCsv(){
        return GUID  + "," + artistGUID + "," + duration + "," + title;
    }

    /**
     * The formatter for Song objects
     * @return the formatted string for Song
     */
    @Override
    public String toString() {
        return "Song{" +
                "GUID='" + GUID + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                "}\n";
    }
}
