package com.company.RequestInterpreter.Requests;

import java.util.HashMap;

/**
 * Help implements the Request class.
 * If the user asks for help as a request,
 * this will display the usage for each command
 */
public class Help implements Request {

    /**
     * Attributes
     */
    private String helperString = "";

    /**
     * Constructor
     */
    public Help(HashMap<String, Request> commands) {
        genHelperString(commands);
    }

    /**
     * Takes in all of the commands available and prints out their usage description to the user
     * @param commands all available commands
     */
    private void genHelperString(HashMap<String, Request> commands) {

        for(String key : commands.keySet()) {
            helperString += String.format("%s : %s\n", key, commands.get(key).getUsageDesc());
        }

    }

    /**
     * prints the helper string
     * @param args  params are unused
     * @return      returns nothing
     */
    @Override
    public Response handle(String args) {
        
        System.out.println(helperString);

        return null;
    }

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    @Override
    public String getUsageDesc() {
        return "Displays usage for each command - No params";
    }

    
    
}