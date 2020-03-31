package com.company.ResponseFormatter.Queries;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

/**
 * @author kkf4497
 * Interface for the different filters for songs, releases, and artists
 */
public interface Filter {
	/**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Any args that the filter needs
	 * @return The filtered Releases
	 */
    public void visitRelease(Release searchable);

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Any args that the filter needs
	 * @return The filtered Songs
	 */
	public void visitSong(Song searchable);

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Any args that the filter needs
	 * @return The filtered Artists
	 */
	public void visitArtist(Artist searchable);

	public List<Searchable> getContents();

	public void clearContents();

	public void setFilterParam(String filterParam);
}
