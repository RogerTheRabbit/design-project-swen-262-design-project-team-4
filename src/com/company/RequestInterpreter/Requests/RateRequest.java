package com.company.RequestInterpreter.Requests;

import com.company.Database.OfflineDatabase;
import com.company.RequestInterpreter.CommandHandler;

/**
 * RateRequest implements the Request class.
 * Takes in a string argument in the handler to provide a rating for a searchable in
 * a user's library
 */
public class RateRequest implements Request {

    /**
     * Attributes
     */
    private CommandHandler commandHandler;

    /**
     * Constructor
     */
    public RateRequest(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    /**
     * handles adding a rating to a song
     * @param args  the rating and guid
     * @return      nothing currently
     */
    @Override
    public Response handle(String args) {

        String[] params = args.split(" ");

        try {
            int rating = Integer.parseInt(params[1]);
            if (rating < 1 || rating > 5) {
                throw new ExceptionInInitializerError();
            }
            commandHandler.rateSearchableInLibrary(params[0], rating);

            System.out.println("Successfully rated song/artist in your personal library!");

        } catch(NullPointerException e) {
            System.err.println("That song/release is not in your personal library and therefore can not be rated.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid command. Command should be used as 'rate [GUID] [rating (1-5)]'");
        } catch (ExceptionInInitializerError e) {
            System.err.println("Rating must be between 1 and 5 inclusive.");
        } catch (Exception e) {
            System.err.println("Failed to rate song...");
        }



        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Rate a song - {GUID} {rating [1-5]}";
    }

    
}