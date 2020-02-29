package com.company.RequestInterpreter.Requests;

/**
 * QueryRequest
 */
public class QueryRequest implements Request {

    @Override
    public Response handle(String args) {
        System.out.println("Grabbing those results for you...");
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Searches songs based on input";
    }

    
}