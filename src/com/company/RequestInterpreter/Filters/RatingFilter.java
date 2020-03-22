package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This implementation of Filter allows you to filter search results by Ratings.
 * The user provides a minimum rating and this filters any Searchable objects below that rating.
 */
public class RatingFilter implements Filter {
    
	/**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Releases
	 */
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {
        
        LinkedList<Release> filteredReleases = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Release release : values) {
            if(release.getRating() >= minRating) {
                filteredReleases.add(release);
            }
        }

        if(filteredReleases.size() == 0) {
            System.out.printf("No releases were found with ratings higher than %d\n", minRating);
        }

        return filteredReleases;
        
    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Songs
	 */
    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        
        LinkedList<Song> filteredSong = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Song song : values) {
            if(song.getRating() >= minRating) {
                filteredSong.add(song);
            }
        }

        if(filteredSong.size() == 0) {
            System.out.printf("No songs were found with ratings higher than or equal to %d\n", minRating);
        }

        return filteredSong;
    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Artists
	 */
    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        
        LinkedList<Artist> filteredReleases = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Artist artist : values) {
            if(artist.getRating() >= minRating) {
                filteredReleases.add(artist);
            }
        }

        if(filteredReleases.size() == 0) {
            System.out.printf("No releases were found with ratings higher than %d\n", minRating);
        }

        return filteredReleases;

    }
}
