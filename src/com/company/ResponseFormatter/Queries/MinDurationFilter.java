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
 * MinDurationFilter. Filters based on if searchables are 
 * over a specified duration
 */
public class MinDurationFilter implements Filter {
    private List<Searchable> searchables;
    private String searchValue;

    public MinDurationFilter() {
        this.searchables = new ArrayList<>();
        this.searchValue = "";
    }

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Minimum duration of release
	 * @return The filtered Releases
	 */
    @Override
    public void visitRelease(Release release) {


        int minLength;

        try {
            minLength = Integer.parseInt(searchValue);
            if (release.getTotalDuration() >= minLength) {
                searchables.add(release);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
        }



    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Minimum duration of song
	 * @return The filtered Songs
	 */
    @Override
    public void visitSong(Song song) {


        int minLength;

        try {
            minLength = Integer.parseInt(searchValue);
            if (song.getTotalDuration() >= minLength) {
                searchables.add(song);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
        }



    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Minimum duration of artist
	 * @return The filtered Artists
	 */
    @Override
    public void visitArtist(Artist artist) {

        int minLength;

        try {
            minLength = Integer.parseInt(searchValue);
            if (artist.getTotalDuration() >= minLength) {
                searchables.add(artist);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number.");
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
