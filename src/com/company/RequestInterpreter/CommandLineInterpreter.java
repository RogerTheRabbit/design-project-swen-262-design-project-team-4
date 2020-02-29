package com.company.RequestInterpreter;

import java.util.HashMap;

import com.company.Database.Database;
import com.company.FileIO.FileParser;
import com.company.RequestInterpreter.Requests.Request;
import com.company.RequestInterpreter.Requests.testRequest;

/**
 * CommandLineInterpreter
 */
public class CommandLineInterpreter {

    static final HashMap<String, Request> COMMANDS = new HashMap<>() {{
        put("test", new testRequest());
    }};

}