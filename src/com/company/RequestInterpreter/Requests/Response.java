package com.company.RequestInterpreter.Requests;

import java.util.ArrayList;

import com.company.Database.Searchable;

/**
 * Response **Not currently being used**
 * 
 */
public class Response {

    /**
     * Attributes
     */
    private ArrayList<Searchable> content;
    private boolean successStatus;

    /**
     * Constructor
     */
    Response(ArrayList<Searchable> content, boolean successStatus) {
        this.content = content;
        this.successStatus = successStatus;
    }

    /**
     * Overloaded Constructor
     */
    Response(boolean successStatus) {
        this.content = new ArrayList<>(1);
        this.successStatus = successStatus;
    }

    /**
     * gets the content contained within the response
     * @return  the array of content in response to the request
     */
    public ArrayList<Searchable> getContent(){
        return content;
    }

    /**
     * gets the response's success status(a boolean)
     * @return
     */
    public boolean getSuccessStatus() {
        return successStatus;
    }

}
