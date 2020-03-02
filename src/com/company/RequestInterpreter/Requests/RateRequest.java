package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * RateRequest implements the Request class.
 * Takes in a string argument in the handler to provide a rating for a searchable in
 * a user's library
 */
public class RateRequest implements Request {

    private Database database;

    /**
     * Constructor
     */
    public RateRequest(Database database) {
        this.database = database;
    }

    @Override
    public Response handle(String args) {
        System.out.println("Updating rating in your personal library!");

        String[] params = args.split(" ");

        try {
            int rating = Integer.parseInt(params[1]);
            if (rating < 1 || rating > 5) {
                throw new Exception();
            }
            database.rateSearchableInLibrary(params[0], rating);
        } catch (Exception e) {
            System.err.println("Invalid command. Command should be used as 'rate [GUID] [rating (1-5)]'");
        }

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "{GUID} {rating [1-5]}";
    }

    
}