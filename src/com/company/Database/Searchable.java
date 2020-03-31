package com.company.Database;

import com.company.ResponseFormatter.Queries.Filter;

import java.util.Date;
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
    public String getArtistGUID();
    public String getName();
    public String getGUID();
    public Integer getRating();
    public String formatToCsv();
    public void setAcquisitionDate(Date date);
    public Date getAcquisitionDate();
    public int hashCode();
    public void accept(Filter filter);
}
