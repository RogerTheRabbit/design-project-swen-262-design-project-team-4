package com.company.RequestInterpreter.Requests;

import com.company.Database.OfflineDatabase;
import com.company.RequestInterpreter.CommandHandler;

/**
 * SetFilterRequest
 * sets the filter for the database's response
 */
public class SetFilterRequest implements Request {

    /**
     * Attributes
     */
    private CommandHandler commandHandler;

    /**
     * Constructor
     */
    public SetFilterRequest(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    /**
     * takes the inputted desired filter string and passes that to the database for it to set the filter
     * @param args  the things that you are performing the request on
     * @return      currently nothing
     */
    @Override
    public Response handle(String args) {
        commandHandler.setFilter(args.trim());
        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return String.format("Set what criteria you would like to search by - {search filter %s}", offlineDatabase.getAvailableFilterTypes());
    }

    
}