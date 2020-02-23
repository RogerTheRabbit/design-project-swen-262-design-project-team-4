package com.company.FileIO;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private Scanner scan;

    public FileParser(String filePath, String filename) {
        try {

            var path = Paths.get(filePath, filename);
            File file = path.toFile();
            this.scan = new Scanner(file);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public ArrayList<String[]> readFile(){
        ArrayList<String[]> allLines = new ArrayList<>();
        while(scan.hasNext()){
            String[] lineArray = scan.nextLine().split(",");
            allLines.add(lineArray);
        }
        return allLines;
    }

}
