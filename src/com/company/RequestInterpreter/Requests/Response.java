package com.company.RequestInterpreter.Requests;

import java.util.ArrayList;

import com.company.Database.Searchable;

/**
 * Response
 * 
 */
public class Response {

    private ArrayList<Searchable> content;
    private boolean successStatus;

    Response(ArrayList<Searchable> content, boolean successStatus) {
        this.content = content;
        this.successStatus = successStatus;
    }

    Response(boolean successStatus) {
        this.content = new ArrayList<>(1);
        this.successStatus = successStatus;
    }

    public ArrayList<Searchable> getContent(){
        return content;
    }

    public boolean getSuccessStatus() {
        return successStatus;
    }

}
