package com.company.MusicBrainz;

import com.company.Database.Artist;
import com.company.Database.Database;
import com.company.Database.Release;
import com.company.Database.Song;

public class OnlineDatabase implements Database {
    @Override
    public Song getSong(String GUID) {
        return null;
    }

    @Override
    public Artist getArtist(String GUID) {
        return null;
    }

    @Override
    public Release getRelease(String GUID) {
        return null;
    }
}