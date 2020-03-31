package com.company.ResponseFormatter.Queries;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This implementation of Filter allows you to filter search results by Ratings.
 * The user provides a minimum rating and this filters any Searchable objects below that rating.
 */
public class RatingFilter implements Filter {
    private List<Searchable> searchables;
    private String searchValue;

    public RatingFilter() {
        this.searchables = new ArrayList<>();
        this.searchValue = "";
    }

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Releases
	 */
    @Override
    public void visitRelease(Release release) {
        int minRating = Integer.parseInt(searchValue);

            if(release.getRating() >= minRating) {
                searchables.add(release);
            }

    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Songs
	 */
    @Override
    public void visitSong(Song song) {

        int minRating = Integer.parseInt(searchValue);

        if(song.getRating() >= minRating) {
            searchables.add(song);
        }
    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Minimum rating
	 * @return The filtered Artists
	 */
    @Override
    public void visitArtist(Artist artist) {
        int minRating = Integer.parseInt(searchValue);

        if(artist.getRating() >= minRating) {
            searchables.add(artist);
        }

    }

    @Override
    public List<Searchable> getContents() {
        return searchables;
    }

    @Override
    public void clearContents() {
        searchables = new ArrayList<>();
    }

    @Override
    public void setFilterParam(String filterParam) {
        searchValue = filterParam;
    }
}
