package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturesCacheTest {

    @Test
    void returnList() {
        LecturesCache cash = new LecturesCache();
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new LectureIModel(LectureType.JAVA_CORE, "\"JAVA_CORE Java API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, Calendar.OCTOBER, 12),3));
        Calendar currentDate = new GregorianCalendar(2020, Calendar.OCTOBER, 12);
        cash.setCurrentDate(currentDate);
        assertEquals(lectures, cash.returnList());

    }
}