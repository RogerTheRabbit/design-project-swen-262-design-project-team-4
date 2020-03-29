package com.company.SearchableFactory;

import com.company.Database.Artist;
import com.company.Database.Searchable;
import com.company.MusicBrainz.ArtistSearch;

public class ArtistFactory {

    /**
     * Factory method for handling creation of an Artist
     * @param fields The attributes of the artist
     * @return The Artist object (a Searchable)
     */
    public Artist makeArtistFromCsv(String[] fields){
        return new Artist(fields[0], fields[1], fields[2]);
    }

    public Artist makeArtistFromMusicBrainz(ArtistSearch artist){
        return null;
    }
}
