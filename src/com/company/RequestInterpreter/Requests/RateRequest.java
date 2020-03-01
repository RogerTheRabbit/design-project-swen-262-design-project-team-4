package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * RateRequest
 */
public class RateRequest implements Request {

    private Database database;

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
        return "Rate a song in your personal library";
    }

    
}