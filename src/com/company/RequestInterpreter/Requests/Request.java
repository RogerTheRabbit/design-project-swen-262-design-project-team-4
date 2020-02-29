package com.company.RequestInterpreter.Requests;

/**
 * Request
 * 
 * Represents every type of request a user can make to the system.
 * handle() should take care of actually completing a user's request.
 */
public interface Request {

    public Response handle(String args);

    public String getUsageDesc();

}
