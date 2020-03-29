package com.company.SearchableFactory;

import com.company.Database.Searchable;
import com.company.Database.Song;

public class SongFactory {

    /**
     * Factory method for handling creation of a Song
     * @param fields The attributes of the song
     * @return The Song object (a Searchable)
     */
    public Song makeSongFromCsv(String[] fields){
        Song searchable = new Song(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
        return searchable;
    }
}
