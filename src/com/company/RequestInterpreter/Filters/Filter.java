package com.company.RequestInterpreter.Filters;

import java.util.Collection;

import com.company.Database.Searchable;
import com.company.Database.Song;

public interface Filter {

    public Collection<Searchable> filter(Collection<Searchable> toFilter, String filterParam);

	public Collection<Searchable> filterSongs(Collection<Song> values, String searchValue);
}
