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

    /**
     * Attributes
     */
    private String guid;
    private String artistGUID;
    private String title;
    private Date issueDate;
    private Medium medium;
    private List<Searchable> songList;
    private int duration;
    private Date acquisitionDate;


    /**
     * Constructor
     * 
     * @param guid GUID of release
     * @param artistGUID GUID of artist that made the release
     * @param title Title of the release
     * @param issueDate Date the release was made
     * @param medium Type of medium the release is made of
     * @param songList List of songs that are in this release
     */
    public Release(String guid, String artistGUID, String title, Date issueDate, Medium medium, List<Searchable> songList) {
        this.title = title;
        this.guid = guid;
        this.duration = calculateDuration(songList);
        this.artistGUID = artistGUID;
        this.songList = songList;
        this.medium = medium;
        this.issueDate = issueDate;
        this.acquisitionDate = null;
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

    /**
     * gets the rating of the release (release rating = average of song ratings)
     * @return rating
     */
    @Override
    public Integer getRating() {
        int average = 0;
        int numSongs = 0;
        for(Searchable song: songList){
            int rating = song.getRating();
            average += rating;
            if(rating != 0){
                numSongs++;
            }
        }
        if(numSongs == 0){
            return 0;
        }
        return average/numSongs;
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
     * gets the issue date of the release
     * @return issueDate
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * Returns a release in csv notation for the @FileSaver
     * @return csv format of a release
     */
    public String formatToCsv(){
        String result = guid + "," + artistGUID + "," + title + "," + issueDate + "," + medium;
        for(Searchable song: songList){
            result += song.getGUID();
        }
        return result;
    }

    /**
     * toString() for Releases
     */
    @Override
    public String toString() {
        return "Release{" +
                "guid='" + guid +
                ", issueDate=" + issueDate +
                ", medium=" + medium +
                ", rating=" + getRating() +
                ", duration=" + duration +
                ", title='" + title +
                "}\n";
    }

}
