package com.company.RequestInterpreter.Requests;

/**
 * SelectRequest
 */
public class SelectArtist implements Request {

    @Override
    public Response handle(String args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Selects artist in your personal library {GUID of artist to select [GUID]}";
    }

    
}