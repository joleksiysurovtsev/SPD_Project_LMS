package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;

class Point1Menu implements ITerminal {
    public static LecturesCache cash = LecturesCache.getInstance();
    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LectureValidator lectureValidator = new LectureValidator();

    @Override
    public void showContext() {
        ConsoleMassage.MESSAGE_HEAD_MENU_POINT_1.printMassage();
        String choice = ConsoleInputValidator.readString();
        try {
            switch (choice.toLowerCase()) {
                case "+":
                    print.printAllLectureTable(lectureServiceImpl.getLectures());
                    break;
                case "-":
                    ConsoleMassage.MESSAGE_ENTER_NUMBERS_SEP.printMassage();
                    print.printLectureListByNumber(ConsoleInputValidator.readString(), lectureServiceImpl.getLectures());
                    break;
                case "small":
                    print.printPreviewLectureList(lectureServiceImpl.getLectures());
                    break;
                case "type":
                    print.printLectureListByType(lectureValidator.selectLectureType(), lectureServiceImpl.getMapSortedByType());
                    break;
                case "date":
                    cash.setCurrentDate(ConsoleInputValidator.enterTheDate());
                    print.printAllLectureTable(cash.returnList());
                    break;
                case "type and date":
                    print.printLectureListByTypeAndDate(lectureValidator.selectLectureType(), lectureServiceImpl.getMapSortedByType(), ConsoleInputValidator.enterTheDate());
                    break;
                case "exit":
                    LMSTerminal.startLMS();
                    break;
                default:
                    ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
                    showContext();
                    break;
            }
        } catch (ListIsEmptyException e) {
            System.err.println(e.getMessage());
        }
        ConsoleMassage.MESSAGE_Q_WHAT_TO_DO_NEXT.printMassage();
        subMenuShowLectures();
    }

    private void subMenuShowLectures() {
        String s = ConsoleInputValidator.readString().toUpperCase();
        if ("0".equals(s)) {
            showContext();
        } else if ("EXIT".equals(s)) {
            System.exit(0);
        } else {
            ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
            ConsoleMassage.MESSAGE_ERR_YOU_MUST_ENTER_EITHER.printMassage();
            subMenuShowLectures();
        }
    }
}