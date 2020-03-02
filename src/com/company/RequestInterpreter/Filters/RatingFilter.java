package com.company.RequestInterpreter.Filters;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Song;

import java.util.Collection;
import java.util.LinkedList;

public class RatingFilter implements Filter {
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {
        
        LinkedList<Release> filteredReleases = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Release release : values) {
            if(release.getRating() >= minRating) {
                filteredReleases.add(release);
            }
        }

        if(filteredReleases.size() == 0) {
            System.out.printf("No releases were found with ratings higher than %d\n", minRating);
        }

        return filteredReleases;
        
    }

    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        
        LinkedList<Song> filteredSong = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Song song : values) {
            if(song.getRating() >= minRating) {
                filteredSong.add(song);
            }
        }

        if(filteredSong.size() == 0) {
            System.out.printf("No songs were found with ratings higher than %d\n", minRating);
        }

        return filteredSong;
    }

    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> values, String searchValue) {
        
        LinkedList<Artist> filteredReleases = new LinkedList<>();

        int minRating = Integer.parseInt(searchValue);

        for(Artist artist : values) {
            if(artist.getRating() >= minRating) {
                filteredReleases.add(artist);
            }
        }

        if(filteredReleases.size() == 0) {
            System.out.printf("No releases were found with ratings higher than %d\n", minRating);
        }

        return filteredReleases;

    }
}
