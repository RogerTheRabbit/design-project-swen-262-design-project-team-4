package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

public interface Filter {

    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue);

	public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue);
}
