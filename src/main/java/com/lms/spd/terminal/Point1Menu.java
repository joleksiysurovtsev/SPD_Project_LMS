package com.lms.spd.terminal;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.LectureValidator;
import com.lms.spd.utils.Util;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LectureType;
import com.lms.spd.error.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import java.util.List;

class Point1Menu implements ITerminal {
    private LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LectureValidator lectureValidator = new LectureValidator();

    @Override
    public void showContext() {
        ConsoleMassage.MESSAGE_HEAD_MENU_POINT_1.printMassage();
        String choice = ConsoleInputValidator.readString();
        try {
            switch (choice.toLowerCase()) {
                case "+"://✅
                    print.printAllLectureTable(lectureServiceImpl.getItems());
                    break;
                case "-"://✅
                    ConsoleMassage.MESSAGE_ENTER_NUMBERS_SEP.printMassage();
                    print.printAllLectureTable(lectureServiceImpl.getLecturesByNumber(Util.getStringsNumberLecture(ConsoleInputValidator.readString())));
                    break;
                case "small"://✅
                    print.printPreviewLectureList(lectureServiceImpl.getItems());
                    break;
                case "type"://✅
                    LectureType lectureType = lectureValidator.selectLectureType();
                    List<Lecture> lectureListByType = lectureServiceImpl.getLectureListByType(lectureType);
                    print.printAllLectureTable(lectureListByType);
                    break;
                case "date"://✅
                    print.printAllLectureTable(lectureServiceImpl.getLectureListByDate(ConsoleInputValidator.enterTheDate()));
                    break;
                case "type and date"://✅
                    List<Lecture> lectureListByTypeAndDate = lectureServiceImpl.getLectureListByTypeAndDate(
                            lectureValidator.selectLectureType(), ConsoleInputValidator.enterTheDate());
                    print.printAllLectureTable(lectureListByTypeAndDate);
                    break;
                case "exit"://✅
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