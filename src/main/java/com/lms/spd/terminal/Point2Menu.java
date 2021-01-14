package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;

class Point2Menu implements ITerminal {

    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LectureValidator lectureValidator = new LectureValidator();

    @Override
    public void showContext() {
        lectureServiceImpl.addLecture(lectureValidator.createLecture());
        ConsoleMassage.MESSAGE_Q_ENTER_LECTURE.printMassage();
        subMenuAddingLectureToList();
    }

    private void subMenuAddingLectureToList() {
        ConsoleMassage.MESSAGE_Q_WHAT_TO_DO_NEXT_2.printMassage();
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
                subMenuAddingLectureToList();
                break;
        }
    }
}

