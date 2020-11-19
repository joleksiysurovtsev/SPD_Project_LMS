package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturesCashTest {

    @Test
    void returnList() {
        LecturesCash cash = new LecturesCash();
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new LectureIModel(LectureType.JAVA_CORE, 3, "\"JAVA_CORE Java API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 9, 12)));
        Calendar curentDate = new GregorianCalendar(2020, Calendar.OCTOBER, 12);
        cash.setCurentDate(curentDate);
        assertEquals(lectures, cash.returnList());
    }
}

