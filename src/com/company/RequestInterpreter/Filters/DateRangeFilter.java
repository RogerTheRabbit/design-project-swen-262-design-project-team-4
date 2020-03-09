package com.company.RequestInterpreter.Filters;

import java.util.Collection;
import java.util.LinkedList;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.SearchableMaker;
import com.company.Database.Song;

/**
 * DateRangeFilter. Filters based on a given date range. Songs within 
 * the specified date range 
 */
public class DateRangeFilter implements Filter {

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Releases
	 */
    @Override
    public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

        String[] params = searchValue.split(" ");
        if (params.length != 2) {
            System.err.println(
                    "Invalid date range format.  Please specify date as [start date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] [end date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] inclusive");
            return new LinkedList<Release>();
        }

        Long startDate;
        Long endDate;

        try {
            startDate = SearchableMaker.makeDate(params[0].trim()).getTime();
            endDate = SearchableMaker.makeDate(params[1].trim()).getTime();
        } catch (Exception e) {
            System.err.println(
                    "One or both of the dates provided was in the incorrect format. Please specify dates as (YYYY-MM-DD)|(YYYY-MM)|(YYYY)");
            return new LinkedList<Release>();
        }

        LinkedList<Release> filteredReleases = new LinkedList<>();

        for (Release release : values) {
            if (release.getIssueDate().getTime() >= startDate && release.getIssueDate().getTime() <= endDate) {
                filteredReleases.add(release);
            }
        }

        return filteredReleases;
    }

	/**
	 * Defines how filter should handle Songs. Can not filter songs by date-range
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Songs
	 */
    @Override
    public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {
        System.err.println("Error: Trying to filter songs by date range. This is not possible. You can only filter releases by date-range");
        return new LinkedList<Song>();
    }

	/**
	 * Defines how filter should handle Artists. Can not filter artists by date-range
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Artists
	 */
    @Override
    public LinkedList<Artist> filterArtists(Collection<Artist> someSongs, String searchValue) {
        System.err.println("Error: Trying to filter artists by date range. This is not possible. You can only filter releases by date-range");
        return new LinkedList<Artist>();
    }
}
