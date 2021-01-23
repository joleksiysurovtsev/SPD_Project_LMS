package com.lms.spd.util;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.utils.LectureCollectorByType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LectureCollectorByTypeTest {

    @Test
    void collectToSortedMapByType() {
        Lecture lecture1 = new LectureIModel("Test",new ArrayList<>(),new GregorianCalendar(2020,10,15),"testLector", LectureType.JAVA_CORE,1,120);
        Lecture lecture2 = new LectureIModel("Test2",new ArrayList<>(),new GregorianCalendar(2020,11,15),"testLector", LectureType.JAVA_CORE,2,120);
        Lecture lecture3 = new LectureIModel("Test3",new ArrayList<>(),new GregorianCalendar(2020,12,15),"testLector", LectureType.COMMON,3,120);
        List <Lecture> testList = List.of(lecture1,lecture2,lecture3);

        Map<LectureType, List<Lecture>> expected = new HashMap<>();
        expected.put(LectureType.JAVA_CORE,List.of(lecture1,lecture2));
        expected.put(LectureType.COMMON,List.of(lecture3));
        assertEquals(expected,testList.stream().collect(LectureCollectorByType.collectToSortedMapByType()));
    }
}