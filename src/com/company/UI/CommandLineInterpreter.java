package com.company.UI;

import java.util.HashMap;
import java.util.Scanner;

import com.company.RequestInterpreter.*;
import com.company.RequestInterpreter.Requests.*;

/**
 * CommandLineInterpreter
 */
public class CommandLineInterpreter {
    
    // Initialize everything here

    // Map of all commands: key = command that user types into terminal | value =
    // Request to handle that command.
    private HashMap<String, Request> commands;



    public CommandLineInterpreter() {
        initializeCommands();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please input a username");
        String username = in.nextLine();

        System.out.println("Setting everything up...");

        CommandLineInterpreter interpreter = new CommandLineInterpreter();

        // Start main program loop
        System.out.println("Ready!");
        System.out.println("For a list of commands, please type : \"help\"");
        while (true) {
            String input = in.nextLine();
            String command = input.split(" ")[0].toLowerCase();
            if(interpreter.getCommandMap().containsKey(command)) {
                interpreter.getCommand(command).handle(input.substring(command.length()).trim());
            } else if(input.equals("exit")) {
                interpreter.getCommand("save").handle(username);
                break;
            // TODO: Make this a new command
            } else if(input.trim().toLowerCase().equals("cls")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                // Command is invalid so display help.
                System.out.println("Unknown command. Type 'help' to get command usage");
            }
        }

        in.close();

    }

    private void initializeCommands(){
        commands = new HashMap<>();
        CommandHandler commandHandler = new CommandHandler();
        // Add Commands here
        // Note: Keys should always be lowercase
        commands.put("add", new AddToLibraryRequest(commandHandler));
        commands.put("rate", new RateRequest(commandHandler));
        commands.put("remove", new RemoveFromLibraryRequest(commandHandler));
        commands.put("search", new SearchDatabaseRequest(commandHandler));
        commands.put("searchlibrary", new SearchLibraryRequest(commandHandler));
        commands.put("setfilter", new SetFilterRequest(commandHandler));
        commands.put("setsort", new SetSortRequest(commandHandler));
        commands.put("browse", new BrowseRequest(commandHandler));
        commands.put("selectartist", new SelectArtist(commandHandler));
        commands.put("selectrelease", new SelectRelease(commandHandler));
        commands.put("selectsong", new SelectSong(commandHandler));
        commands.put("help", new Help(commands));
    }

    public Request getCommand(String name){
        return commands.get(name);
    }

    public HashMap<String, Request> getCommandMap(){
        return commands;
    }


}
