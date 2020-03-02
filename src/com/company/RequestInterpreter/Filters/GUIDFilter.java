package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

/**
 * GUIDFilter
 */
public class GUIDFilter implements Filter {

    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for (Release release : values) {
            if (release.getGUID().equals(searchValue)) {
                filteredReleases.add(release);
            }
        }

        return filteredReleases;
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredReleases = new LinkedList<>();

        for (Song song : values) {
            if (song.getGUID().equals(searchValue)) {
                filteredReleases.add(song);
            }
        }

        return filteredReleases;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        LinkedList<Artist> filteredReleases = new LinkedList<>();

        for (Artist artist : values) {
            if (artist.getGUID().equals(searchValue)) {
                filteredReleases.add(artist);
            }
        }

        return filteredReleases;
    }

}