package com.company.ResponseFormatter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

/**
 * GUIDFilter. Filters based on a given GUID. Returns searchables 
 * that have the same GUID as a given GUID
 */
public class GUIDFilter implements Filter {

	/**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue GUID of desired release
	 * @return The filtered Releases
	 */
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for (Release release : values) {
            if (release.getGUID().contains(searchValue)) {
                filteredReleases.add(release);
            }
        }

        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue GUID of desired Song
	 * @return The filtered Songs
	 */
    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredReleases = new LinkedList<>();

        for (Song song : values) {
            if (song.getGUID().contains(searchValue)) {
                filteredReleases.add(song);
            }
        }

        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue GUID of given Artist
	 * @return The filtered Artists
	 */
    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        LinkedList<Artist> filteredReleases = new LinkedList<>();

        for (Artist artist : values) {
            if (artist.getGUID().contains(searchValue)) {
                filteredReleases.add(artist);
            }
        }

        return filteredReleases;
    }

}