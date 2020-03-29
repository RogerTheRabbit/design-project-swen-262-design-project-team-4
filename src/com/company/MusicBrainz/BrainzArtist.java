package com.company.MusicBrainz;

import com.google.gson.annotations.SerializedName;

public class BrainzArtist {
    private String id;
    private String name;
    @SerializedName("sort-name")
    private String sortName;
}
