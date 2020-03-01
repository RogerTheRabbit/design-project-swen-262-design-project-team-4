package com.company.RequestInterpreter.Filters;

import java.util.Collection;

import com.company.Database.Searchable;
import com.company.Database.Song;

/**
 * DateRangeFilter
 */
public class DateRangeFilter implements Filter {

    @Override
    public Collection<Searchable> filter(Collection<Searchable> toFilter, String filterParam) {
        return null;
    }

    @Override
    public Collection<Searchable> filterSongs(Collection<Song> values, String searchValue) {
        // TODO Auto-generated method stub
        return null;
    }
}
