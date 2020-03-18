package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

/**
 * MaxDurationFilter. Filters songs based on if they are under 
 * a specified duration.
 */
public class MaxDurationFilter implements Filter {
    
	/**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Maximum duration of song
	 * @return The filtered Releases
	 */
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        int maxLength;

        try {
            maxLength = Integer.parseInt(searchValue);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
            return new LinkedList<>();
        }

        for (Release release : values) {
            if (release.getTotalDuration() <= maxLength) {
                filteredReleases.add(release);
            }
        }
        if(filteredReleases.size() == 0) {
            System.out.printf("No releases less than %d ms were found.\n", maxLength);
        }
        
        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Maximum duration of song
	 * @return The filtered Songs
	 */
    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredSongs = new LinkedList<>();

        int maxLength;

        try {
            maxLength = Integer.parseInt(searchValue);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
            return new LinkedList<>();
        }

        for (Song song : values) {
            if (song.getTotalDuration() <= maxLength) {
                filteredSongs.add(song);
            }
        }
        if(filteredSongs.size() == 0) {
            System.out.printf("No songs less than %d ms were found.\n", maxLength);
        }
        
        return filteredSongs;
    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Maximum duration of song
	 * @return The filtered Artists
	 */
    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        LinkedList<Artist> filteredArtists = new LinkedList<>();

        int maxLength;

        try {
            maxLength = Integer.parseInt(searchValue);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
            return new LinkedList<>();
        }

        for (Artist artist : values) {
            if (artist.getTotalDuration() <= maxLength) {
                filteredArtists.add(artist);
            }
        }
        if(filteredArtists.size() == 0) {
            System.out.printf("No releases less than %d ms were found.\n", maxLength);
        }
        
        return filteredArtists;
    }
}
