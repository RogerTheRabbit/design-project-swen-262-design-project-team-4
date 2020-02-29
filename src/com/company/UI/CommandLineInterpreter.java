package com.company.UI;

import java.util.HashMap;
import java.util.Scanner;

import com.company.Database.Database;
import com.company.RequestInterpreter.Requests.*;

/**
 * CommandLineInterpreter
 */
public class CommandLineInterpreter {

    // Map of all commands: key = command that user types into terminal | value = Request to handle that command.
    private static final HashMap<String, Request> COMMANDS;
    static {
        COMMANDS = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        COMMANDS.put("add", new AddToLibraryRequest());
        COMMANDS.put("query", new QueryRequest());
        COMMANDS.put("rate", new RateRequest());
        COMMANDS.put("remove", new RemoveFromLibraryRequest());
        COMMANDS.put("help", new help(COMMANDS));
        
    }

    public static void main(String[] args) {

        System.out.println("Setting everything up...");

        Scanner in = new Scanner(System.in);

        //TODO: Initialize everything here


        Database database = new Database();

        // Start main program loop
        System.out.println("Ready!");
        while (true) {
            String input = in.nextLine();
            String command = input.split(" ")[0].toLowerCase();
            if(COMMANDS.containsKey(command)) {
                COMMANDS.get(command).handle(input.substring(command.length()));
            } else if(input.equals("exit")) {
                break;
            } else {
                // Command is invalid so display help.
                System.out.printf("Type 'help' to get command usage");
            }
        }

        in.close();

    }

}
