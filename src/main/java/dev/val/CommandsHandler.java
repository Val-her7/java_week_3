package dev.val;

public class CommandsHandler {
    public Commands command;

    public CommandsHandler(Commands command){
        this.command = command;
    }

    public void execute(String input){
        String[] inputCommand = input.trim().split(" ");
        String instruction = inputCommand[0];

        switch(instruction){
            
        }
    }
}
