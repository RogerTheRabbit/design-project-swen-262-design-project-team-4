package com.company.Database;

import java.util.List;

/**
 * @author mjh9131
 * Interface for the searchable items which is Song, Release, and Artist
 *
 * design pattern: Composite
 * role in pattern: Component
 */
public interface Searchable {
    public int getTotalDuration();
    public List<Searchable> getSongList();
    public Searchable getArtist();
    public String getName();
    public String getGUID();
}
