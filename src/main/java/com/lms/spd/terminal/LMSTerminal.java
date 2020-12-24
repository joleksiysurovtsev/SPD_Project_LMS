package com.lms.spd.terminal;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;

import java.util.Map;

public class LMSTerminal {
    private static final LMSConsolePrinter print = new LMSConsolePrinter();
    private static StartMenu menu;

    private static final Map<Integer, StartMenu> menuPoints = Map.of(
            1, new StartMenu(new Point1Menu()),
            2, new StartMenu(new Point2Menu()),
            3, new StartMenu(new Point3Menu()),
            4, new StartMenu(new Point4Menu()),
            0, new StartMenu(new Point0Menu())
    );

    public static void startLMS() {
        while (true) {
            print.showStartMenu();
            try {
                menu = menuPoints.get(ConsoleInputValidator.readInt());
            } catch (NullPointerException e) {
                System.err.println("No such menu item exists try again");
                startLMS();
            }
            menu.executeMenu();
        }
    }
}
