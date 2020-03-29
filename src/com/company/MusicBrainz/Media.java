package com.company.MusicBrainz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
    private int position;
    private List<Track> tracks;
    @SerializedName("track-count")
    private int trackCount;
    @SerializedName("track-offset")
    private int trackOffset;
}
