package com.company.FileIO;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {

    private String filePath;
    private String fileName;

    public FileParser(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String[]> readFile(){
        try{
            var path = Paths.get(filePath, fileName);
            File file = path.toFile();
            Scanner scan = new Scanner(file);

            ArrayList<String[]> allLines = new ArrayList<>();
            while(scan.hasNext()){
                String[] lineArray = scan.nextLine().split(",");
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
