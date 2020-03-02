package com.company.UI;

import java.util.HashMap;
import java.util.Scanner;

import com.company.Database.Database;
import com.company.RequestInterpreter.Requests.*;

/**
 * CommandLineInterpreter
 */
public class CommandLineInterpreter {
    
    //TODO: Initialize everything here

    static Database database = new Database();

    // Map of all commands: key = command that user types into terminal | value =
    // Request to handle that command.
    private static final HashMap<String, Request> COMMANDS;
    static {
        COMMANDS = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        COMMANDS.put("add", new AddToLibraryRequest(database));
        COMMANDS.put("rate", new RateRequest(database));
        COMMANDS.put("remove", new RemoveFromLibraryRequest(database));
        COMMANDS.put("search", new SearchDatabaseRequest(database));
        COMMANDS.put("searchlibrary", new SearchLibraryRequest(database));
        COMMANDS.put("help", new Help(COMMANDS));
        COMMANDS.put("setfilter", new SetFilterRequest(database));
        COMMANDS.put("setsort", new SetSortRequest(database));
    }

    public static void main(String[] args) {

        System.out.println("Setting everything up...");

        Scanner in = new Scanner(System.in);

        // Start main program loop
        System.out.println("Ready!");
        while (true) {
            String input = in.nextLine();
            String command = input.split(" ")[0].toLowerCase();
            if(COMMANDS.containsKey(command)) {
                COMMANDS.get(command).handle(input.substring(command.length()).trim());
            } else if(input.equals("exit")) {
                database.saveLibrary();
                break;
            } else {
                // Command is invalid so display help.
                System.out.println("Type 'help' to get command usage");
            }
        }

        in.close();

    }

}
