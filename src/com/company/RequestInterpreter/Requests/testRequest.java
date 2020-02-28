package com.company.RequestInterpreter.Requests;

/**
 * testRequest
 */
public class testRequest implements Request {

    public Response handle(String args) {
        System.out.println("Test Command Successfully handled!");
        return new Response(true);
    }
}
