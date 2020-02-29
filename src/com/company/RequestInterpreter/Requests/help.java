package com.company.RequestInterpreter.Requests;

import java.util.HashMap;

/**
 * help
 */
public class help implements Request {

    private String helperString = "";

    public help(HashMap<String, Request> commands) {
        genHelperString(commands);
    }

    private void genHelperString(HashMap<String, Request> commands) {

        for(String key : commands.keySet()) {
            helperString += String.format("%s : %s\n", key, commands.get(key).getUsageDesc());
        }

    }

    @Override
    public Response handle(String args) {
        
        System.out.println(helperString);

        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Displays usage for each command";
    }

    
    
}