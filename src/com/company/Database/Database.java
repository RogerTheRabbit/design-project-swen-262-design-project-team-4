package com.company.Database;

public interface Database {
    public Song getSong(String GUID);
    public Artist getArtist(String GUID);
    public Release getRelease(String GUID);
}
