package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

/**
 * This implementation of Filter allows you to filter search results by Name.
 * The user provides a name and this filters out any Searchable objects that do not contain that name.
 */
public class NameFilter implements Filter {

	/**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Name to search for
	 * @return The filtered Releases
	 */
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for (Release release : values) {
            if (release.getName().contains(searchValue)) {
                filteredReleases.add(release);
            }
        }

        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Name to search for
	 * @return The filtered Songs
	 */
    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        LinkedList<Song> filteredReleases = new LinkedList<>();

        for (Song song : values) {
            if (song.getName().contains(searchValue)) {
                filteredReleases.add(song);
            }
        }

        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Name to search for
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