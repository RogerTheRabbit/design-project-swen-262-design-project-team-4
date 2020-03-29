package com.company.MusicBrainz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrainzRelease {
    private String id;
    private int count;
    private String title;
    private String status;
    @SerializedName("release-group")
    private String date;
    private String country;
    @SerializedName("track-count")
    private int trackCount;
    private List<Media> media;
}
