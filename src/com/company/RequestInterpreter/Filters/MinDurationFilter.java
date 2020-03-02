package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

public class MinDurationFilter implements Filter{
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        LinkedList<Release> filteredReleases = new LinkedList<>();

        int minLength = Integer.parseInt(searchValue);

        for (Release release : values) {
            if (release.getTotalDuration() >= minLength) {
                filteredReleases.add(release);
            }
        }
        if(filteredReleases.size() != 0) {
            return filteredReleases;
        }

        System.out.printf("No releases longer than %d ms were found.\n", minLength);

        return null;
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {

        LinkedList<Song> filteredSongs = new LinkedList<>();

        int minLength = Integer.parseInt(searchValue);

        for (Song song : values) {
            if (song.getTotalDuration() >= minLength) {
                filteredSongs.add(song);
            }
        }
        if(filteredSongs.size() != 0) {
            return filteredSongs;
        }

        System.out.printf("No songs longer than %d ms were found.\n", minLength);

        return null;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        LinkedList<Artist> filteredArtists = new LinkedList<>();

        int minLength = Integer.parseInt(searchValue);

        for (Artist artist : values) {
            if (artist.getTotalDuration() >= minLength) {
                filteredArtists.add(artist);
            }
        }
        if(filteredArtists.size() != 0) {
            return filteredArtists;
        }

        System.out.printf("No releases longer than %d ms were found.\n", minLength);

        return null;
    }
}
