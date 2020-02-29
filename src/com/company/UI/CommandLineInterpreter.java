package com.company.UI;

import java.util.HashMap;
import java.util.Scanner;

import com.company.RequestInterpreter.Requests.Request;
import com.company.RequestInterpreter.Requests.testRequest;

/**
 * CommandLineInterpreter
 */
public class CommandLineInterpreter {

    // Map of all commands: key = command that user types into terminal | value = Request to handle that command.
    private static final HashMap<String, Request> COMMANDS;
    static {
        COMMANDS = new HashMap<>();
        // Add Commands here
        // Note: Values should always be lowercase
        COMMANDS.put("test", new testRequest());  
    }

    public static void main(String[] args) {

        System.out.println("Setting everything up...");

        Scanner in = new Scanner(System.in);

        //TODO: Initialize everything here




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
                System.out.printf("Invalid command: %s\nPlease use one of the following commands:\n- %s\n- exit\n", command, String.join("- \n", COMMANDS.keySet()));
            }
        }

        in.close();

    }

}
