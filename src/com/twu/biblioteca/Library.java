package com.twu.biblioteca;

import java.io.IOException;

/**
 * Created by derekgilwa on 6/20/14.
 */
public class Library {

    private CommandMenu commandMenu;

    public Library(CommandMenu commandMenu){

        this.commandMenu = commandMenu;
    }

    public void start() throws IOException {
        commandMenu.displayWelcome();
        commandMenu.listOptions();

        boolean isValidCommand = commandMenu.selectAndExecuteOption();
        while(!isValidCommand){
            isValidCommand = commandMenu.selectAndExecuteOption();
        }
    }
}