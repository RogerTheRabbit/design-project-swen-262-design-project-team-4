package com.company.MusicBrainz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recording {
    private String id;
    private int score;
    private String title;
    private int length;
    private String video;
    @SerializedName("artist-credit")
    private List<ArtistCredit> artistCredits;
    private List<BrainzRelease> releases;
}
