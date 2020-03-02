package com.company.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.company.FileIO.FileSaver;

import java.io.File;
import java.util.*;

/**
 * Library
 * 
 */
public class Library {

    private String username = "Jimmy";
    private Database database;
    private FileSaver FILEWRITER;
    private HashSet<Searchable> searchables;
    private HashMap<Artist, Searchable> artistMap;

    Library(Database database) {
        this.database = database;
        FILEWRITER = FileSaver.getInstance();
        searchables = new HashSet<>();
        artistMap = new HashMap<>();
    }

    public Collection<Searchable> getSearchables(String searchableGUID) {
        return searchables;
    }

    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        Searchable songToAdd = database.getSearchable(searchableGUID);

        if(songToAdd != null) {
            searchables.add(songToAdd);
            return true;
        }
        return false;
    }

    public void addAcquisitionDate(String guid, Date accDate){
        database.getSong(guid).setAcquisitionDate(accDate);
    }

    public boolean removeSearchable(String searchableGUID) {
        Searchable songToRemove = database.getSearchable(searchableGUID);

        // TODO: Make sure that if all songs from an artist are removed from the database, remove the artist as well.

        if(songToRemove != null) {

            String artistGUID = songToRemove.getArtistGUID();

            searchables.remove(songToRemove);

            return true;
        }
        return false;
    }

    boolean addRating(String searchableGUID, Integer rating) {
        database.getSong(searchableGUID).setRating(rating);
        return false;
    }

    public ArrayList<Searchable> seperateSearchables(String searchableType){

        ArrayList<Searchable> seperatedSearchables = new ArrayList<>();

        if(searchableType.equalsIgnoreCase("Artist")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Artist){
                    seperatedSearchables.add(searchable);
                }
            }
        }
        else if(searchableType.equalsIgnoreCase("Release")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Release){
                    seperatedSearchables.add(searchable);
                }
            }
        }
        else if(searchableType.equalsIgnoreCase("Song")){
            for(Searchable searchable: searchables){
                if (searchable instanceof Song){
                    seperatedSearchables.add(searchable);
                }
            }
        }

        return seperatedSearchables;
    }

    public void saveLibrary(){
        File artistFile = FILEWRITER.makeFile(username, "Artists");
        ArrayList<Searchable> artists = seperateSearchables("Artist");
        FILEWRITER.saveSearchables(artistFile, artists);

        File songsFile = FILEWRITER.makeFile(username, "Songs");
        ArrayList<Searchable> songs = seperateSearchables("Song");
        FILEWRITER.saveSearchables(songsFile, songs);

        File releasesFile = FILEWRITER.makeFile(username, "Releases");
        ArrayList<Searchable> releases = seperateSearchables("Release");
        FILEWRITER.saveSearchables(releasesFile, releases);


        File ratingFile = FILEWRITER.makeFile(username, "Ratings");
        FILEWRITER.saveHashmap(ratingFile, ratingsToHashMap(seperateSearchables("Song")));

        File acquisitionDateFile = FILEWRITER.makeFile(username, "Dates");
        Collection<Song> someSongs = new ArrayList<>((Collection<? extends Song>) songs);
        FILEWRITER.saveHashmap(acquisitionDateFile, datesToHashMap(someSongs));
    }

    public  HashMap<String, Integer> ratingsToHashMap(Collection<Searchable> searchables){
        HashMap<String, Integer> ratingsMap = new HashMap<>();
        for(Searchable song: searchables){
            ratingsMap.put(song.getGUID(), song.getRating());
        }
        return ratingsMap;
    }


    public  HashMap<String, Date> datesToHashMap(Collection<Song> searchables){
        HashMap<String, Date> ratingsMap = new HashMap<>();
        for(Song song: searchables){
            ratingsMap.put(song.getGUID(), song.getAcquisitionDate());
        }
        return ratingsMap;
    }


}
