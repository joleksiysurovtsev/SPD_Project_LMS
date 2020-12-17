package com.lms.spd;

import com.lms.spd.lmsjdbc.JDBCConnector;

public class Main {

    public static void main(String[] args) {

        LMSTerminal lmsTerminal = new LMSTerminal();
        lmsTerminal.startLMS();
    }
}
