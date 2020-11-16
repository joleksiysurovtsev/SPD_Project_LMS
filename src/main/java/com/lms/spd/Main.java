package com.lms.spd;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LMSTerminal lmsTerminal = new LMSTerminal();
        System.out.println(LMSUtilsHelper.generateId());
        System.out.println(LMSUtilsHelper.generateId());
        System.out.println(LMSUtilsHelper.generateId());
        try {
            lmsTerminal.startLMS();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
