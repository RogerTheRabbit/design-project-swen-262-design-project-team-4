package com.company.RequestInterpreter.Requests;

/**
 * RemoveFromLibraryRequest
 */
public class RemoveFromLibraryRequest implements Request {

    @Override
    public Response handle(String args) {
        System.out.println("Removing from your personal Library!");
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Remove song from your library";
    }

    
}