package com.company.RequestInterpreter.Requests;

/**
 * SelectRequest
 */
public class SelectSong implements Request {

    @Override
    public Response handle(String args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsageDesc() {
        return "Selects the song in your personal library; {GUID of song to select [GUID]}";
    }

}