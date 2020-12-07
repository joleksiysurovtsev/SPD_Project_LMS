package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

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
        System.out.println("Lecture duration");
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
        System.out.println("Enter lecturer name");
        return ConsoleInputValidator.readString();
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    public LectureType selectLectureType() {
        System.out.println("Please, choose lecture type: ");
        IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
        LectureType lectureType;
        while (true) {
            int number = ConsoleInputValidator.readInt() - 1;
            lectureType = LectureType.stream().filter(d -> d.ordinal() == number).findFirst().orElse(null);
            if (lectureType == null) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return lectureType;
    }
}
