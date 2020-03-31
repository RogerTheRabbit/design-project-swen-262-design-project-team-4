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
 * GUIDFilter. Filters based on a given GUID. Returns searchables 
 * that have the same GUID as a given GUID
 */
public class GUIDFilter implements Filter {

    private List<Searchable> searchables;
    private String searchValue;

    public GUIDFilter() {
        this.searchables = new ArrayList<>();
        this.searchValue = "";
    }

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue GUID of desired release
	 * @return The filtered Releases
	 */
    @Override
    public void visitRelease(Release searchable) {


            if (searchable.getGUID().contains(searchValue)) {
                searchables.add(searchable);

        }

    }

	/**
	 * Defines how filter should handle Songs
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue GUID of desired Song
	 * @return The filtered Songs
	 */
    @Override
    public void visitSong(Song searchable) {

            if (searchable.getGUID().contains(searchValue)) {
                searchables.add(searchable);
            }

    }

	/**
	 * Defines how filter should handle Artists
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue GUID of given Artist
	 * @return The filtered Artists
	 */
    @Override
    public void visitArtist(Artist artist) {

            if (artist.getGUID().contains(searchValue)) {
                searchables.add(artist);
            }

    }

    @Override
    public List<Searchable> getContents(){
        return searchables;
    }

    @Override
    public void clearContents() {
        searchables = new ArrayList<>();
    }

    public void setFilterParam(String filterParam){
        this.searchValue = filterParam;
    }

}