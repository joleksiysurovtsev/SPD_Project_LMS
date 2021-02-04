package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

class Point3Menu implements ITerminal {

    private LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();

    @Override
    public void showContext() {
        ConsoleMassage.MESSAGE_HEAD_MENU_POINT_3.printMassage();
        int[] arr = Util.getStringsNumberLecture(ConsoleInputValidator.readString());
        if (arr == null) {
            subMenuRemovalLecture();
        } else {
            lectureServiceImpl.removeItems(arr);
        }
        subMenuRemovalLecture();
    }

    private void subMenuRemovalLecture() {
        ConsoleMassage.MESSAGE_Q_DELETE_ANOTHER_ONE.printMassage();
        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                showContext();
                break;
            case "-":
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
                ConsoleMassage.MESSAGE_ERR_YOU_MUST_ENTER_EITHER2.printMassage();
                subMenuRemovalLecture();
                break;
        }
    }
}
