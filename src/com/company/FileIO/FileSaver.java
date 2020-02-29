package com.company.FileIO;

public class FileSaver {

    private static FileSaver fileSaver = null;

    public static FileSaver getInstance(){
        if(fileSaver == null){
            fileSaver = new FileSaver();
        }

        return fileSaver;
    }
}
