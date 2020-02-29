package com.company.FileIO;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileSaver {

    private static FileSaver fileSaver = null;

    public static FileSaver getInstance(){
        if(fileSaver == null){
            fileSaver = new FileSaver();
        }

        return fileSaver;
    }

    public void saveLibrary(String username, Collection<Searchable> searchables, HashMap<String, Integer> ratings){
        File artistFile = makeFile(username, "Artists");
        ArrayList<Searchable> artists = seperateSearchables(searchables, "Artist");
        writeToFile(artistFile, artists);

        File songsFile = makeFile(username, "Songs");
        ArrayList<Searchable> songs = seperateSearchables(searchables, "Song");
        writeToFile(songsFile, songs);

        File releasesFile = makeFile(username, "Releases");
        ArrayList<Searchable> releases = seperateSearchables(searchables, "Release");
        writeToFile(releasesFile, releases);


        File ratingFile = makeFile(username, "Ratings");
        saveRatings(ratingFile, ratings);

    }

    private void writeToFile(File file, Collection<Searchable> searchables){
        try {
            FileWriter myWriter = new FileWriter(file);
            for(Searchable entry: searchables){
                myWriter.write(entry.formatToCsv());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<Searchable> seperateSearchables(Collection<Searchable> searchables, String searchableType){

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

    private File makeFile(String username, String searchableType){
        try {
            File myObj = new File("src/data/user/", (username + searchableType + ".csv"));
            if (myObj.createNewFile()) {
                return myObj;
            } else {
                System.err.println("File already exists.         :err in FileSaver");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    private void saveRatings(File ratingFile, HashMap<String, Integer> ratings){
        try {
            FileWriter myWriter = new FileWriter(ratingFile);

            for(Map.Entry rating: ratings.entrySet()){
                String guid = (String) rating.getKey();
                Integer value = (Integer) rating.getValue();
                myWriter.write(guid + "," + value);
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
