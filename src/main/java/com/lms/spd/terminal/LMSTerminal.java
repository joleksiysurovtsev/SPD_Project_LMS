package com.lms.spd.terminal;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;

import java.util.Map;

public class LMSTerminal {
    private static final LMSConsolePrinter print = new LMSConsolePrinter();
    private static StartMenuStrategyConstructor menu;

    private static final Map<Integer, StartMenuStrategyConstructor> menuPoints = Map.of(
            1, new StartMenuStrategyConstructor(new Point1Menu()),
            2, new StartMenuStrategyConstructor(new Point2Menu()),
            3, new StartMenuStrategyConstructor(new Point3Menu()),
            4, new StartMenuStrategyConstructor(new Point4Menu()),
            0, new StartMenuStrategyConstructor(new Point0Menu())
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
