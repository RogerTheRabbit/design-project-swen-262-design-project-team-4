package com.company.Database;

import com.company.RequestInterpreter.Response;

public interface Database {
    public Song getSong(String GUID);
    public Artist getArtist(String GUID);
    public Release getRelease(String GUID);
    public Response getSongs();
    public Response getReleases();
    public Response getArtists();
}
