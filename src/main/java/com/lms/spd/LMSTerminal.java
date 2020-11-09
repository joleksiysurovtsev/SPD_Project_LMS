package com.lms.spd;

import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static LectureService lectureServiceImpl = new LectureServiceImpl();
    public static LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    public static LMSConsolePrinter lmsConsolePrinter = new LMSConsolePrinter();

    public static void startLMS() {
        lmsConsolePrinter.showStartMenu();
        try {
            switch (reader.readLine()) {
                case "1":
                    new LMSTerminalPoint1();
                    break;
                case "2":
                    new LMSTerminalPoint2();
                    break;
                case "3":
                    new LMSTerminalPoint3();
                    break;
                case "4":
                    new LMSTerminalPoint4();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
