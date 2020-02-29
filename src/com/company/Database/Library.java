package com.company.Database;

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
    // TODO: Decide to keep using HashMap or switch to TreeSet
    // GUID , rating
    private HashMap<String, Integer> ratings;

    Library(Database database) {
        this.database = database;
        FILEWRITER = FileSaver.getInstance();
        searchables = new HashSet<>();
        ratings = new HashMap<String, Integer>();
    }


    public String getUsername() {
        return username;
    }

    public boolean addSearchable(String searchableGUID) {

        List<Searchable> songToAdd = database.getSearchable(searchableGUID).getSongList();

        if(songToAdd != null) {
            searchables.addAll(songToAdd);
            return true;
        }
        return false;
    }

    public boolean removeSearchable(String searchableGUID) {
        List<Searchable> songToRemove = database.getSearchable(searchableGUID).getSongList();

        if(songToRemove != null) {
            searchables.removeAll(songToRemove);
            return true;
        }
        return false;
    }

    boolean addRating(String searchableGUID, Integer rating) {
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
        FILEWRITER.saveHashmap(ratingFile, ratings);
    }


}
