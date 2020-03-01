package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Release;
import com.company.Database.Song;

/**
 * GUIDFilter
 */
public class GUIDFilter implements Filter {

    @Override
    public Collection<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for(Release release : values) {
            if(release.getGUID().equals(searchValue)) {
                filteredReleases.add(release);
            }
        }
        
        return filteredReleases;
    }

    @Override
    public Collection<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredReleases = new LinkedList<>();

        for(Song release : values) {
            if(release.getGUID().equals(searchValue)) {
                filteredReleases.add(release);
            }
        }
        
        return filteredReleases;
    }

    
}