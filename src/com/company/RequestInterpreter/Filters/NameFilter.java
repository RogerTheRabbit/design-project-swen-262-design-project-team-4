package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

/**
 * NameFilter
 */
public class NameFilter implements Filter {

    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> someSongs, String searchValue) {
        // TODO Auto-generated method stub
        return null;
    }


    
}