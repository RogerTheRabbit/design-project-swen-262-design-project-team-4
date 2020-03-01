package com.company.RequestInterpreter.Filters;

import java.util.Collection;

import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;

public interface Filter {

    public Collection<Searchable> filter(Collection<Searchable> toFilter, String filterParam);

    public Collection<Release> filterReleases(Collection<Release> values, String searchValue);

	public Collection<Song> filterSongs(Collection<Song> values, String searchValue);
}
