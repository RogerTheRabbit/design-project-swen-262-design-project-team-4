package com.company.RequestInterpreter.Requests;

import com.company.Database.Database;

/**
 * Request
 */
public interface Request {

    public Response handle(String args);
    
}