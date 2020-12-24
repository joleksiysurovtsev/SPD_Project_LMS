package com.lms.spd.terminal;

public class StartMenuStrategyConstructor {

    ITerminal terminal ;

    public StartMenuStrategyConstructor(ITerminal terminal) {
        this.terminal = terminal;
    }

    public void executeMenu(){
        terminal.showContext();
    }
}
