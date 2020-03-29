package com.company.RequestInterpreter;

/**
 * Request
 * 
 * Represents every type of request a user can make to the system.
 * handle() should take care of actually completing a user's request.
 */
public interface Request {

    /**
     * handles what to do depending on the request that implements the request interface
     * and the parameters provided
     * @param args  the things that you are performing the request on
     * @return      will return a response, currently doesn't return anything tho
     */
    public Response handle(String args);

    /**
     * how the request should be formatted
     * @return  the string representation of how the request params should be formatted
     */
    public String getUsageDesc();

}
