package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

public class MaxDurationFilter implements Filter{
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        int maxLength = Integer.parseInt(searchValue);

        for (Release release : values) {
            if (release.getTotalDuration() <= maxLength) {
                filteredReleases.add(release);
            }
        }
        if(filteredReleases.size() != 0) {
            return filteredReleases;
        }

        System.out.printf("No releases less than %d ms were found.\n", maxLength);

        return null;
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredSongs = new LinkedList<>();

        int maxLength = Integer.parseInt(searchValue);

        for (Song song : values) {
            if (song.getTotalDuration() <= maxLength) {
                filteredSongs.add(song);
            }
        }
        if(filteredSongs.size() != 0) {
            return filteredSongs;
        }

        System.out.printf("No songs less than %d ms were found.\n", maxLength);

        return null;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        LinkedList<Artist> filteredArtists = new LinkedList<>();

        int maxLength = Integer.parseInt(searchValue);

        for (Artist artist : values) {
            if (artist.getTotalDuration() <= maxLength) {
                filteredArtists.add(artist);
            }
        }
        if(filteredArtists.size() != 0) {
            return filteredArtists;
        }

        System.out.printf("No releases less than %d ms were found.\n", maxLength);

        return null;
    }
}
