package com.company.RequestInterpreter.Requests;

import java.util.Date;

import com.company.Database.Database;
import com.company.Database.SearchableMaker;

/**
 * AddToLibraryRequest
 */
public class AddToLibraryRequest implements Request {

    private Database database;

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

        database.addSearchableToLibrary(params[0], date);

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "{GUID} {date [YYYY-MM-DD]}";
    }

    
}