package com.company.SearchableFactory;

import com.company.Database.Artist;
import com.company.Database.Searchable;

public class ArtistFactory {

    /**
     * Factory method for handling creation of an Artist
     * @param fields The attributes of the artist
     * @return The Artist object (a Searchable)
     */
    public Artist makeArtistFromCsv(String[] fields){
        return new Artist(fields[0], fields[1], fields[2]);
    }
}
