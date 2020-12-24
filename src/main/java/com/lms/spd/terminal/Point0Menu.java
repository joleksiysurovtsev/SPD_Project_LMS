package com.lms.spd.terminal;

public class Point0Menu implements ITerminal {
    @Override
    public void showContext() {
        System.exit(0);
    }
}
