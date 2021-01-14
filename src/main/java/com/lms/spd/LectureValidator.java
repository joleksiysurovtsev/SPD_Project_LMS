package com.lms.spd;

import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;

import java.util.Calendar;
import java.util.List;

public class LectureValidator {
    private LiteratureValidator literatureValidator = new LiteratureValidator();
    private LectureServiceImpl lectureService = new LectureServiceImpl();

    public Lecture createLecture() {
        String nameOfLecture = createTheLectureTitle();
        String lectorName = enterLektorName();
        LectureType lectureType = selectLectureType();
        Calendar lectureDate = ConsoleInputValidator.enterTheDate();
        int lectureDuration = lectureDuration();
        int ID = lectureService.generateLectureID();
        List<Literature> literatures = literatureValidator.addLitOrNot();
        return new LectureIModel(nameOfLecture, literatures, lectureDate, lectorName, lectureType, ID, lectureDuration);
    }

    private int lectureDuration() {
        ConsoleMassage.MESSAGE_ENTER_LECTURE_DURATION.printMassage();
        return ConsoleInputValidator.readInt();
    }

    /**
     * Returns the title of the lecture after checking that it is not empty.
     */
    private String createTheLectureTitle() {
        return ConsoleInputValidator.readString("lectureName");
    }

    /**
     * Returns a string with the name of the lecturer, if no name is entered then the name is unknown
     */
    private String enterLektorName() {
        ConsoleMassage.MESSAGE_ENTER_LECTURER_NAME.printMassage();
        return ConsoleInputValidator.readString();
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    public LectureType selectLectureType() {
        ConsoleMassage.MESSAGE_CHOOSE_LECTURE_TYPE.printMassage();
        LectureType lectureType;
        while (true) {
            int number = ConsoleInputValidator.readInt() - 1;
            lectureType = LectureType.stream().filter(d -> d.ordinal() == number).findFirst().orElse(null);
            if (lectureType != null) {
                break;
            } else {
                ConsoleMassage.MESSAGE_ERR_UNKNOWN_TYPE.printMassage();
            }
        }
        return lectureType;
    }
}
