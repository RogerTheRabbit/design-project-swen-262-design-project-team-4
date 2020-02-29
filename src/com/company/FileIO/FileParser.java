package com.company.FileIO;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author mjh9131
 *
 * given a file path and a file name, this class will store them
 * the user can also retrieve and change the file name and file path in question
 * when the user wants, they can try to parse the file
 * if the file is a csv file it will parse it and return an array list of string arrays
 * each string array in the arraylist being the line in the file
 * and the entries in the string array being the comma seperated fields
 *
 */
public class FileParser {

    /**
     * Attributes
     */
    private String filePath;
    private String fileName;

    /**
     * Constructor
     */
    public FileParser() {
        this.filePath = "";
        this.fileName = "";
    }

    /**
     * getter for the filepath
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * setter for filePath
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *getter for the fileName
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * setter for the fileName
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Uses the saved filepath and filename to read through the file
     * for each line. split it into a string array based on the commas,
     * then add it to an ArrayList
     *
     * @return Arraylist of all the lines, which are String arrays containg the csvs
     */
    public ArrayList<String[]> readFile(){
        try{
            var path = Paths.get(filePath, fileName);
            File file = path.toFile();
            Scanner scan = new Scanner(file);

            ArrayList<String[]> allLines = new ArrayList<>();
            while(scan.hasNext()){
                String[] lineArray = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                allLines.add(lineArray);
            }

            return allLines;
        }
        catch (Exception e){
            System.err.println(e);
        }
        return null;
    }

}
