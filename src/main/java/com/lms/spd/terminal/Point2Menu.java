package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

class Point2Menu implements ITerminal {

    private LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();
    private LectureValidator lectureValidator = new LectureValidator();
    private LiteratureValidator literatureValidator = new LiteratureValidator();

    @Override
    public void showContext() {
        Lecture lecture = lectureServiceImpl.addItem(lectureValidator.createLecture());
        List<Literature> literature = literatureValidator.addLitOrNot();
        if (!literature.isEmpty()) {
            List<Integer> integers = literature.stream().map(Literature::getId).collect(Collectors.toList());
            lectureServiceImpl.addLinkLiteratureLectures(lecture.getId(), integers);
        }
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

