package com.company.Database;

import com.company.ResponseFormatter.Queries.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mjh9131
 *
 * an artist which contains a list of all its songs and releases
 * along with identifying information of the artist
 *
 * design pattern: Composite
 * role in pattern: Composite
 */
public class Artist implements Searchable {

    /**
     * Attributes
     */
    private String guid;
    private String name;
    private String disambiguation;
    private int duration;
    private List<Searchable> discography;

    /**
     * Constructor
     * @param name  name of the artist
     * @param guid  unique guid of the artist
     * @param disambiguation a unique identifier to distinguish artists with the same name
     */
    public Artist(String guid, String name, String disambiguation) {
        this.name = name;
        this.guid = guid;
        this.disambiguation = disambiguation;
        this.duration = 0;
        this.discography = new ArrayList<>();
    }

    /**
     * iterates through the discography of the artist and calculates the total duration
     * @param discography all the content the artist has produced
     * @return
     */
    public int calculateDuration(List<Searchable> discography){
        int total = 0;
        for (Searchable currentSearchable : discography) {
            total += currentSearchable.getTotalDuration();
        }
        return total;
    }

    /**
     * to add a searchable to the artist's discography
     * @param searchable the searchable to be added to the artist's discography
     */
    public void addSearchable(Searchable searchable){
        discography.add(searchable);
        duration = calculateDuration(discography);
    }

    /**
     * gets the total duration of the artist's discography
     * @return duration
     */
    @Override
    public int getTotalDuration() {
        return duration;
    }

    /**
     * the list of songs in the artist's discography
     * @return the list of songs
     */
    @Override
    public List<Searchable> getSongList() {
        List<Searchable> songs = new ArrayList<>();
        for(Searchable searchable: discography){
            songs.addAll(searchable.getSongList());
        }
        return songs;
    }

    /**
     * returns its guid
     * @return its guid
     */
    @Override
    public String getArtistGUID() {
        return guid;
    }

    /**
     * gets the name of the artist
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the unique guid of the artist
     * @return guid
     */
    @Override
    public String getGUID() {
        return guid;
    }

    /**
     * gets the average rating of all of the artist's discography
     * @return rating
     */
    @Override
    public Integer getRating() {
        int average = 0;
        int numSongs = 0;
        for(Searchable song: discography){
            int rating = song.getRating();
            average += rating;
            if(rating != 0){
                numSongs++;
            }
        }
        return average/numSongs;
    }

    /**
     * takes the artist information and puts it into a csv format
     * in case you need to write the artist info to a file
     * @return String in csv format
     */
    public String formatToCsv(){
        return guid + "," + name + "," + disambiguation;
    }

    /**
     * does nothing, but exists so that acquisition dates
     * for the songs and releases works in the context of searchables
     * @param date the acquisition date
     */
    @Override
    public void setAcquisitionDate(Date date) {
    }

    /**
     * artists don't contain an acquisition date
     * @return null
     */
    @Override
    public Date getAcquisitionDate() {
        return null;
    }

    /**
     * makes the artist printable with its important info
     * @return
     */
    @Override
    public String toString() {
        return "Artist{" +
                "guid='" + guid + '\'' +
                ", disambiguation='" + disambiguation + '\'' +
                ", duration=" + duration +
                ", name='" + name + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return duration == artist.duration &&
                guid.equals(artist.guid) &&
                name.equals(artist.name) &&
                disambiguation.equals(artist.disambiguation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, name, disambiguation, duration);
    }

    @Override
    public void accept(Filter filter) {
        filter.visitArtist(this);
    }
}
