package com.company.RequestInterpreter.Requests;

import java.util.Date;

import com.company.Database.Database;
import com.company.SearchableFactory.DateMaker;

/**
 * AddToLibraryRequest is an implementation of the Request class.
 * It adds a Searchable object to the user's library.
 */
public class AddToLibraryRequest implements Request {

    /**
     * Attributes
     */
    private Database database;

    /**
     * Constructor
     */
    public AddToLibraryRequest(Database database) {
        this.database = database;
    }

    /**
     * handles the request made by either
     * adding the music specified to the library or not
     * @param args the music to be added to the library
     * @return currently nothing
     */
    @Override
    public Response handle(String args) {

        System.out.println("Adding music to your personal library!");

        String[] params = args.split(" ");

        Date date;

        if(params.length > 1) {
            try {
                DateMaker maker = new DateMaker();
                date = maker.makeDate(params[1]);
            } catch (Exception e) {
                System.err.println("Music not added. Invalid date format. Make sure date is in format YYYY-MM-DD.");
                return null;
            }
        } else {
            date = new Date();
        }

        database.addSearchableToLibrary(params[0], date);

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Add a song to your personal library - {GUID} {date (optional) [YYYY-MM-DD]}";
    }

    
}