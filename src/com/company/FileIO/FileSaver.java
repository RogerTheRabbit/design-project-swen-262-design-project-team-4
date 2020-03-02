package com.company.FileIO;

import com.company.Database.Searchable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 *
 * The FileSaver saves and stores any Searchable items, including Hashmap, into a file
 *
 */
public class FileSaver {

    private static FileSaver fileSaver = null;

    /**
     * Creates a new instance of FileSaver if there is no file to be saved
     * @return fileSaver
     */
    public static FileSaver getInstance(){
        if(fileSaver == null){
            fileSaver = new FileSaver();
        }

        return fileSaver;
    }

    /**
     * Saves the searchable items in the specific file
     * @param file
     * @param searchables
     */
    public void saveSearchables(File file, Collection<Searchable> searchables){
        try {
            FileWriter myWriter = new FileWriter(file);
            for(Searchable entry: searchables){
                myWriter.write(entry.getGUID() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Creates a new csv file
     * @param username
     * @param searchableType
     * @return myObj
     */
    public File makeFile(String username, String searchableType){
        try {
            File myObj = new File("src/data/user/", (username + searchableType + ".csv"));
            myObj.createNewFile();
            return myObj;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the hashmap of song GUIDs and their ratings
     * @param ratingFile
     * @param ratings
     * @param <K>
     * @param <V>
     */
    public <K, V> void saveHashmap(File ratingFile, HashMap<K, V> ratings){
        try {
            FileWriter myWriter = new FileWriter(ratingFile);

            for(Map.Entry rating: ratings.entrySet()){
                K guid = (K) rating.getKey();
                V value = (V) rating.getValue();
                myWriter.write(guid.toString() + "," + value.toString() + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }
}
