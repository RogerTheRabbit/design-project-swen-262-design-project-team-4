package com.company.Database;

import java.util.List;

public interface Searchable {
    public int getTotalDuration();
    public List<Searchable> getSongList();
    public Searchable getArtist();
    public String getName();
    public String getGUID();
}
