package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class LectureValidator {
    LiteratureValidator literatureValidator = new LiteratureValidator();

    public Lecture createLecture() {
        String nameOfLecture = createTheLectureTitle();
        String lectorName = enterLektorName();
        LectureType lectureType = selectLectureType();
        Calendar lectureDate = ConsoleInputValidator.enterTheDate();
        List<Literature> literatures = literatureValidator.addLitOrNot();
        return new LectureIModel(lectureType, nameOfLecture, literatures, lectorName, lectureDate);
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


    public LectureType selectLectureType(List<LectureType> types) {
        int number = 0;
        while (true) {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(0, types.size()).mapToObj(i -> (i + 1) + ". " + types.get(i) + " ").forEach(System.out::println);
            number = ConsoleInputValidator.readInt();
            if (number > types.size() || number <= 0) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return types.get(number - 1);
    }


    public List<LectureType> arrayLecturesTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }

}
