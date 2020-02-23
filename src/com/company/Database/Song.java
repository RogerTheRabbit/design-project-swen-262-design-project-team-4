package com.company.Database;

import java.util.ArrayList;
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
    private String name;
    private String guid;
    private int duration;
    private Searchable artist;


    /**
     * Constructor
     *
     * @param name
     * @param guid
     * @param duration
     * @param artist
     */
    public Song(String name, String guid, int duration, Searchable artist) {
        this.name = name;
        this.guid = guid;
        this.duration = duration;
        this.artist = artist;
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
    public Searchable getArtist() {
        return artist;
    }

    /**
     * gets the name of the song
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the song's guid
     * @return guid
     */
    @Override
    public String getGUID() {
        return guid;
    }
}
