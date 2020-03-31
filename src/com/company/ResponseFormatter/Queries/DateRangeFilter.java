package com.company.ResponseFormatter.Queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.company.Database.Artist;
import com.company.Database.Release;
import com.company.Database.Searchable;
import com.company.Database.Song;
import com.company.SearchableFactory.DateMaker;

/**
 * DateRangeFilter. Filters based on a given date range. Songs within 
 * the specified date range 
 */
public class DateRangeFilter implements Filter {
    private List<Searchable> searchables;
    private String searchValue;

    public DateRangeFilter() {
        this.searchables = new ArrayList<>();
        this.searchValue = "";
    }

    /**
	 * Defines how filter should handle Releases
	 * 
	 * @param values Collection of Releases to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Releases
	 */
    @Override
    public void visitRelease(Release release) {

        String[] params = searchValue.split(" ");
        if (params.length != 2) {
            System.err.println(
                    "Invalid date range format.  Please specify date as [start date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] [end date (YYYY-MM-DD)|(YYYY-MM)|(YYYY)] inclusive");
        }

        Long startDate;
        Long endDate;

        try {
            DateMaker maker = new DateMaker();
            startDate = maker.makeDate(params[0].trim()).getTime();
            endDate = maker.makeDate(params[1].trim()).getTime();
            if (release.getIssueDate().getTime() >= startDate && release.getIssueDate().getTime() <= endDate) {
                searchables.add(release);
            }
        } catch (Exception e) {
            System.err.println(
                    "One or both of the dates provided was in the incorrect format. Please specify dates as (YYYY-MM-DD)|(YYYY-MM)|(YYYY)");
        }




    }

	/**
	 * Defines how filter should handle Songs. Can not filter songs by date-range
	 * 
	 * @param values Collection of Songs to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Songs
	 */
    @Override
    public void visitSong(Song searchable) {
        System.err.println("Error: Trying to filter songs by date range. This is not possible. You can only filter releases by date-range");
    }

	/**
	 * Defines how filter should handle Artists. Can not filter artists by date-range
	 * 
	 * @param values Collection of Artists to filter
	 * @param searchValue Date range to search for (dates should be separated with a space)
	 * @return The filtered Artists
	 */
    @Override
    public void visitArtist(Artist artist) {
        System.err.println("Error: Trying to filter artists by date range. This is not possible. You can only filter releases by date-range");
    }

    @Override
    public List<Searchable> getContents() {
        return searchables;
    }

    @Override
    public void clearContents() {
        searchables = new ArrayList<>();
    }

    @Override
    public void setFilterParam(String filterParam) {
        this.searchValue = filterParam;
    }
}
