package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.SearchableMaker;
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
    public Collection<Release> filterReleases(Collection<Release> values, String searchValue) {
        
        String[] params = searchValue.split(" ");
        if(params.length != 2) {
            System.err.println("Invalid date range format.  Please specify date as [start date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] [end date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] inclusive");
            return new LinkedList<Release>();
        }

        Long startDate;
        Long endDate;

        try {
            startDate = SearchableMaker.makeDate(params[0].trim()).getTime();
            endDate = SearchableMaker.makeDate(params[1].trim()).getTime();
        } catch (Exception e) {
            System.err.println("One or both of the dates provided was in the incorrect format. Please specify dates as (YYYY-MM-DD)|(YYYY-MM)|(YYYY)");
            return new LinkedList<Release>();
        }

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for(Release release : values) {
            if(release.getIssueDate().getTime() >= startDate && release.getIssueDate().getTime() <= endDate) {
                filteredReleases.add(release);
            }
        }

        return filteredReleases;
    }

    @Override
    public Collection<Song> filterSongs(Collection<Song> values, String searchValue) {
        System.err.println("Error: Trying to filter songs by date range. This is not possible.");
        return new LinkedList<Song>();
    }
}
