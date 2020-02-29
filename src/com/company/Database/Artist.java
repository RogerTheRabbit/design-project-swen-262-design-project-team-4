package com.company.Database;

import java.util.ArrayList;
import java.util.List;

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
    private String name;
    private String guid;
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
     * returns itself
     * @return itself
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

    public String getDisambiguation() {
        return disambiguation;
    }
}
