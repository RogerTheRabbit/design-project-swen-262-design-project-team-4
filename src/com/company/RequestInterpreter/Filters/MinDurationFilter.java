package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

public class MinDurationFilter implements Filter {
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {
        return null;
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        return null;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> someSongs, String searchValue) {
        return null;
    }
}
