package com.company.ResponseFormatter.Queries;

import com.company.Database.Searchable;

import java.util.Iterator;

public interface ResponseIterator {
    public Searchable nextSearchable();
    public boolean isLastSearchable();
    public Searchable currentSearchable();
}
