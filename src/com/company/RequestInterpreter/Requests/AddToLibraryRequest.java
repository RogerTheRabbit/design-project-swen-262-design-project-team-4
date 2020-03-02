package com.company.RequestInterpreter.Requests;

import java.util.Date;

import com.company.Database.Database;
import com.company.Database.SearchableMaker;

/**
 * AddToLibraryRequest is an implementation of the Request class.
 * It adds a Searchable object to the user's library.
 */
public class AddToLibraryRequest implements Request {

    private Database database;

    /**
     * Constructor
     */
    public AddToLibraryRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {

        System.out.println("Adding song to your personal library!");

        String[] params = args.split(" ");

        Date date;

        if(params.length > 1) {
            try {
                date = SearchableMaker.makeDate(params[1]);
            } catch (Exception e) {
                System.err.println("Song not added. Invalid date format. Make sure date is in format YYYY-MM-DD.");
                return null;
            }
        } else {
            date = new Date();
        }

        System.out.println(date);

        database.addSearchableToLibrary(params[0], date);

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "{GUID} {date [YYYY-MM-DD]}";
    }

    
}