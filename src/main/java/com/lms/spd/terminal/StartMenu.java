package com.lms.spd.terminal;

public class StartMenu {

    ITerminal terminal ;

    public StartMenu(ITerminal terminal) {
        this.terminal = terminal;
    }

    public void executeMenu(){
        terminal.showContext();
    }
}
