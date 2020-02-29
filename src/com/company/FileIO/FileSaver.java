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

    public void saveSearchables(File file, Collection<Searchable> searchables){
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

    public File makeFile(String username, String searchableType){
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

    public <K, V> void saveHashmap(File ratingFile, HashMap<K, V> ratings){
        try {
            FileWriter myWriter = new FileWriter(ratingFile);

            for(Map.Entry rating: ratings.entrySet()){
                K guid = (K) rating.getKey();
                V value = (V) rating.getValue();
                myWriter.write(guid.toString() + "," + value.toString());
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
