package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LectureCollectorByDateTest {

    @Test
    void collectToSortedMapByDate() {
        Lecture lecture1 = new LectureIModel("Test",new ArrayList<>(),new GregorianCalendar(2020,10,15),"testLector", LectureType.JAVA_CORE,1,120);
        Lecture lecture2 = new LectureIModel("Test2",new ArrayList<>(),new GregorianCalendar(2020,11,15),"testLector", LectureType.JAVA_CORE,2,120);
        Lecture lecture3 = new LectureIModel("Test3",new ArrayList<>(),new GregorianCalendar(2020,12,15),"testLector", LectureType.COMMON,3,120);
        Lecture lecture4 = new LectureIModel("Test3",new ArrayList<>(),new GregorianCalendar(2020,12,15),"testLector", LectureType.COMMON,4,120);
        List<Lecture> testList = List.of(lecture1,lecture2,lecture3,lecture4);

        Map<Boolean, List<Lecture>> expected = new HashMap<>();
        expected.put(false,List.of(lecture1,lecture2));
        expected.put(true,List.of(lecture3,lecture4));

        assertEquals(expected,testList.stream().collect(LectureCollectorByDate.collectToSortedMapByDate(new GregorianCalendar(2020,11,20))));
    }
}