package com.lms.spd;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LMSTerminal lmsTerminal = new LMSTerminal();

        try {
            lmsTerminal.startLMS();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
