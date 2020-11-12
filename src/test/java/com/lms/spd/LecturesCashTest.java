package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturesCashTest {

    @Test
    void returnList() {
        LecturesCash cash = new LecturesCash();
        LectureService lectureService = new LectureServiceImpl();
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new LectureIModel(LectureType.JAVA_CORE, 1, "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5)));
        lectures.add(new LectureIModel(LectureType.COMMON, 2, "\"Intellij IDEA Features. GitLab flow.\"", new ArrayList<>(), "Andrii Zaiats", new GregorianCalendar(2020, 9, 5)));
        cash.returnList();
        assertEquals(lectures, cash.returnList());
    }
}