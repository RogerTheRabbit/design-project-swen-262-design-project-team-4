package com.company.RequestInterpreter;

import java.util.ArrayList;
import java.util.List;

import com.company.Database.Searchable;

/**
 * Response **Not currently being used**
 * 
 */
public class Response {

    /**
     * Attributes
     */
    private List<Searchable> content;
    private boolean successStatus;
    private String responseMessage;

    /**
     * Constructor
     */
    public Response(List<Searchable> content, boolean successStatus, String responseMessage) {
        this.content = content;
        this.successStatus = successStatus;
        this.responseMessage = responseMessage;
    }

    public Response(List<Searchable> newContent, Response oldResponse){
        this.content = newContent;
        this.successStatus = oldResponse.getSuccessStatus();
        this.responseMessage = oldResponse.getResponseMessage();
    }

    /**
     * Overloaded Constructor
     */
    public Response(boolean successStatus, String responseMessage) {
        this.content = new ArrayList<>(1);
        this.successStatus = successStatus;
        this.responseMessage = responseMessage;
    }

    /**
     * gets the content contained within the response
     * @return  the array of content in response to the request
     */
    public List<Searchable> getContent(){
        return content;
    }

    /**
     * gets the response's success status(a boolean)
     * @return
     */
    public boolean getSuccessStatus() {
        return successStatus;
    }

    public String getResponseMessage(){
        return responseMessage;
    }

}
