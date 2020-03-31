package com.company.ResponseFormatter;

import com.company.Database.Searchable;
import com.company.RequestInterpreter.Response;
import com.company.ResponseFormatter.Queries.*;
import com.company.ResponseFormatter.Sorts.AcquisitionDate;
import com.company.ResponseFormatter.Sorts.Alphabetical;
import com.company.ResponseFormatter.Sorts.Rating;
import com.company.ResponseFormatter.Sorts.Sort;

import java.util.*;

public class ResponseFormatter {
    private Filter filter;
    private Sort sort;
    private HashMap<String, Filter> filters;
    private HashMap<String, Sort> sorts;

    public ResponseFormatter() {
        this.filter = new NameFilter();     // Set default filtering
        this.sort = new Alphabetical();     // Set default sorting
        initializeFilters();
        initializeSorts();
    }

    /**
     * makes the hashmap of possible filters
     */
    private void initializeFilters(){
        filters = new HashMap<>();
        filters.put("name", new NameFilter());
        filters.put("maxduration", new MaxDurationFilter());
        filters.put("minduration", new MinDurationFilter());
        filters.put("guid", new GUIDFilter());
        filters.put("date-range", new DateRangeFilter());
        filters.put("rating", new RatingFilter());
    }

    /**
     * initializes the hashmap of possible sorts
     */
    private void initializeSorts(){
        sorts = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        sorts.put("acquisitiondate", new AcquisitionDate());
        sorts.put("alphabetical", new Alphabetical());
        sorts.put("rating", new Rating());
    }


    /**
     * sets the filter according to what was specified
     * @param filter the name of the filter to be set
     */
    public void setFilter(String filter) {
        if (filters.containsKey(filter.toLowerCase())) {
            this.filter = filters.get(filter.toLowerCase());
            System.out.printf("Filter successfully set to '%s'\n", filter);
        } else {
            System.err.printf("'%s' is an invalid filter.\n", filter);
        }

    }

    /**
     * sets the sort acording to what was specified
     * @param sort the name of the sort to be set
     */
    public void setSort(String sort) {
        if (sorts.containsKey(sort.toLowerCase())) {
            this.sort = sorts.get(sort.toLowerCase());
            System.out.printf("Sort successfully set to '%s'.\n", sort);
        } else {
            System.err.printf("'%s' is not a valid sort\n", sort);
        }
    }

    /**
     * gets the available sort types
     * @return the set of sort names(String)
     */
    public Set<String> getAvailableSortTypes() {
        return sorts.keySet();
    }

    /**
     * gets the available filter types
     * @return the set of filter names
     */
    public Set<String> getAvailableFilterTypes() {
        return filters.keySet();
    }

    public Response formatResponse(Response response){
        Response filteredResponse = filterResponse(response);
        filteredResponse.getContent().sort(sort);
        return response;
    }

    public Response filterResponse(Response response){

        List<Searchable> contents = response.getContent();

        for (Searchable searchable: contents){
            searchable.accept(filter);
        }

        Response newResponse = new Response(filter.getContents(), response);
        filter.clearContents();
        return newResponse;
    }

}
