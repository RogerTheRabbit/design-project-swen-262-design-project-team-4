package com.company.Database;

import java.util.List;

public class Song implements Searchable {
    @Override
    public int getTotalDuration() {
        return 0;
    }

    @Override
    public List<Searchable> getSongList() {
        return null;
    }

    @Override
    public Searchable getArtist() {
        return null;
    }
}
