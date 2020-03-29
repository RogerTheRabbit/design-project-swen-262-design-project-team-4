package com.company.ResponseFormatter.Queries;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
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
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue);

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Any args that the filter needs
	 * @return The filtered Songs
	 */
	public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue);

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Any args that the filter needs
	 * @return The filtered Artists
	 */
	public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue);
}
