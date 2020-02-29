package com.company.RequestInterpreter.Requests;

/**
 * AddToLibraryRequest
 */
public class AddToLibraryRequest implements Request {

    @Override
    public Response handle(String args) {
        System.out.println("Adding song to your personal library!");
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Add a song to your personal library";
    }

    
}