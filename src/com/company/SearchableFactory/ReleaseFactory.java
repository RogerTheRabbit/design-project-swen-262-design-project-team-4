package com.company.SearchableFactory;

import com.company.Database.*;
import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.Date;

public class ReleaseFactory {

    /**
     * Factory method for handling creation of a Release
     * @param fields The attributes of the release
     * @return The Release object (a Searchable)
     */
    public Release makeReleaseFromCsv(String[] fields, Database database) {
        try {
            ArrayList<Searchable> songs = new ArrayList<>();

            for (int i = 5; i < fields.length; i++) {
                Song song = database.getSong(fields[i]);
                songs.add(song);
            }

            Medium medium = Medium.ERROR;
            if(fields[3].equalsIgnoreCase("CD")){
                medium = Medium.CD;
            }
            else if(fields[3].equalsIgnoreCase("Digital Media")){
                medium = Medium.Digital;
            }
            else{
                medium = Medium.Vinyl;
            }
            DateMaker maker = new DateMaker();
            Date date = maker.makeDate(fields[4]);


            Release searchable = new Release(fields[0], fields[1], fields[2], date, medium, songs);
            return searchable;
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
    }
}
