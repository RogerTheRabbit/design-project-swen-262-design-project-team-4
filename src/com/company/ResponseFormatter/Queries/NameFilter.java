package com.company.ResponseFormatter.Queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

/**
 * This implementation of Filter allows you to filter search results by Name.
 * The user provides a name and this filters out any Searchable objects that do not contain that name.
 */
public class NameFilter implements Filter {
    private List<Searchable> searchables;
    private String searchValue;

    public NameFilter() {
        this.searchables = new ArrayList<>();
        this.searchValue = "";
    }

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Name to search for
	 * @return The filtered Releases
	 */
    @Override
    public void visitRelease(Release release) {

            if (release.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                searchables.add(release);
            }

    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Name to search for
	 * @return The filtered Songs
	 */
    @Override
    public void visitSong(Song song) {

            if (song.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                searchables.add(song);
            }

    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Name to search for
	 * @return The filtered Artists
	 */
    @Override
    public void visitArtist(Artist artist) {

            if (artist.getName().toLowerCase().contains(searchValue.toLowerCase())) {
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