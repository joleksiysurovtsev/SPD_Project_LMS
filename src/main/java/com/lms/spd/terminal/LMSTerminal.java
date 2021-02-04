package com.lms.spd.terminal;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.enums.ConsoleMassage;

import java.util.Map;

public class LMSTerminal {

    private static final Map<Integer, StartMenuStrategyConstructor> MENU_POINTS = Map.of(1, new StartMenuStrategyConstructor(new Point1Menu()),
            2, new StartMenuStrategyConstructor(new Point2Menu()),
            3, new StartMenuStrategyConstructor(new Point3Menu()),
            4, new StartMenuStrategyConstructor(new Point4Menu())
    );

    public static void startLMS() {
        StartMenuStrategyConstructor menu;
        while (true) {
            ConsoleMassage.MESSAGE_HEAD_START_MENU.printMassage();
            int choice = ConsoleInputValidator.readInt();
            if (choice == 0) {
                break;
            }
            menu = MENU_POINTS.get(choice);
            if (menu != null) {
                menu.executeMenu();
            } else {
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
            }
        }
    }

    private LMSTerminal() {
    }
}
