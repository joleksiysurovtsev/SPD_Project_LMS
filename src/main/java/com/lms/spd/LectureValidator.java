package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.ArrayList;
import java.util.Arrays;
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
        return new LectureIModel(lectureType, 1, nameOfLecture, literatures, lectorName, lectureDate);
    }

    /**
     * Returns the title of the lecture after checking that it is not empty.
     */
    private String createTheLectureTitle() {
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
    private String enterLektorName() {
        System.out.println("Enter lecturer name");
        String lecturerName = ConsoleInputValidator.readString();
        return lecturerName;
    }

    /**
     * Returns the lecture type implemented by type checking.
     */

    public LectureType selectLectureType() {
        System.out.println("Please, choose lecture type: ");
        IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
        int number = 0;
        while (true) {
            number = ConsoleInputValidator.readInt();
            if (number > LectureType.values().length || number <= 0) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return LectureType.getValueByNumber(number);
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

    public String[] stringToDeleteLecture(String lectureRemove, List<Lecture> list) {
        String[] numbDeletedLect = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, numbDeletedLect.length).forEach(i -> numbDeletedLect[i] = numbDeletedLect[i].replaceAll("[a-zA-Zа]*", ""));
        String[] numbToDisplay = Arrays.stream(numbDeletedLect).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : list) {
                int numb = value.getNumberOfLecture();
                if (numb == Integer.parseInt(item)) {
                    flag = false;
                    stringContains.append(" ").append(item).append(" ");
                    break;
                }
            }
        }
        stringContains.append(!flag ? "successfully removed the rest are missing." : "are missing.");
        System.out.println(stringContains.toString());
        return numbToDisplay;
    }

}
