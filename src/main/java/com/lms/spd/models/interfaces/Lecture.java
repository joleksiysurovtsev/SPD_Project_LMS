package com.lms.spd.models.interfaces;

import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.LectureType;

import java.util.Calendar;
import java.util.List;

public interface Lecture {

    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    List<Literature> getLiteratures();

    void setLiteratures(List<Literature> literatures);

    String getLectorName();

    void setLectorName(String nameOfLecture);

    Calendar getLectureDate();

    void setLectureDate(Calendar lectureDate);

    LectureType getType();

    void setType(LectureType type);


    /**
     * the method print a list of references from the previously selected lecture
     * @param lmsConsolePrinter
     */
    default void printListLit(LMSConsolePrinter lmsConsolePrinter) {
        //создали из лекции лист литературы
        List<Literature> litArr = getLiteratures();
        //если список пустой
        if (litArr.isEmpty()) {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        } else {
             lmsConsolePrinter.sortLectureByDateAndType(litArr);
            //печатаем на экран литературу
            int i = 1;
            for (Literature x : litArr) {
                System.out.println( i + ""+ x.print());
                i++;
            }
        }
    }
}
