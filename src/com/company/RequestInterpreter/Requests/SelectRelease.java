package com.company.RequestInterpreter.Requests;

/**
 * SelectRequest
 */
public class SelectRelease implements Request {

    @Override
    public Response handle(String args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Selects for the release in your personal library; {GUID of release to select [GUID]}";
    }

    
}