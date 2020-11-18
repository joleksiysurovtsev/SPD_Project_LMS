package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

public class LectureValidator {
    LiteratureValidator literatureValidator = new LiteratureValidator();

    public Lecture createLecture() throws IOException {
        String nameOfLecture = createTheLectureTitle();
        String lectorName = enterLektorName();
        LectureType lectureType = selectLectureType();
        Calendar lectureDate = enterTheLectureDate();
        List<Literature> literatures = literatureValidator.addLitOrNot();
        return new LectureIModel(lectureType, 1, nameOfLecture, literatures, lectorName, lectureDate);
    }


    public Calendar enterTheLectureDate() {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            dateInString = ConsoleInputValidator.readString();
            try {
                sdf.setLenient(true);
                d1.setTime(sdf.parse(dateInString));
                break;
            } catch (ParseException e) {
                System.out.println("The date is entered incorrectly, try again");
            }
        }
        return d1;
    }

    /**
     * Returns the title of the lecture after checking that it is not empty.
     */
    public String createTheLectureTitle() {
        String lectureName;
        System.out.println("Enter the title of the lecture");
        while (true) {
            lectureName = ConsoleInputValidator.readString();
            if (!lectureName.isEmpty()) {
                break;
            } else {
                System.out.println("The lecture must have a title. Try again");
            }
        }
        return lectureName;
    }

    /**
     * Returns a string with the name of the lecturer, if no name is entered then the name is unknown
     * returns the title of the lecture after checking that it is not empty.
     */
    String enterLektorName() {
        System.out.println("Enter lecturer name");
        String s = ConsoleInputValidator.readString();
        return s;
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    LectureType selectLectureType() {
        int number = 0;
        while (true) {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
            number = ConsoleInputValidator.readInt();
            if (number > LectureType.values().length || number <= 0) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return LectureType.getValueByNumber(number);
    }

    public List<LectureType> arrayLecturesTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }

    void cashDate(LecturesCache cash){
        cash.setCurentDate(enterTheLectureDate());
    }
}
