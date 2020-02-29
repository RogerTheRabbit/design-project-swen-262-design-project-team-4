package com.company.RequestInterpreter.Requests;

/**
 * RateRequest
 */
public class RateRequest implements Request {

    @Override
    public Response handle(String args) {
        System.out.println("Updating rating in your personal library!");
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Rate a song in your personal library";
    }

    
}