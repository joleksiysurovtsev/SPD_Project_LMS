package com.lms.spd.utils;

import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.pgsql.JDBCConnector;
import com.lms.spd.repository.DBLectureRepository;
import com.lms.spd.repository.DBLiteratureRepository;
import com.lms.spd.repository.interfaces.IRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The method filters the string by removing all elements
     * except digits and commas, and returns an array of these numbers
     */
    public static int[] getStringsNumberLecture(String s) {
        int[] arr = {};
        try {
            arr = Arrays.stream(s.replaceAll("[^0-9,]", "").split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            ConsoleMassage.MESSAGE_ERR_INCORRECT_INPUT.printMassage();
        }
        return arr;
    }

    /**
     *  <b>A method that returns a map in which the key is true / false and the value is List <Lecture>.
     *      * </b> <p>According to the entered date, lectures are grouped into those that have already passed (false)
     *      * and those that still need to be given (true). List is sorted relative to the date of the event.
     **/

    public static Map<Boolean, List<Lecture>> getCollectByDate(LectureType selectLectureType, Map<LectureType, List<Lecture>> mapSortedByType, Calendar currentdate) {
        LectureCollectorByDate collectorByDate = new LectureCollectorByDate(currentdate);
        return mapSortedByType.get(selectLectureType).stream().collect(collectorByDate);
    }

    public static Calendar enterTheDate(String dateAndTime) {
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        try {
            lectureDate.setTime(DATE_FORMAT.parse(dateAndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lectureDate;
    }

    public static Calendar enterTheDateWithoutTime(String dateAndTime) {
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        try {
            lectureDate.setTime(DATE_FORMAT2.parse(dateAndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lectureDate;
    }


    private Util() {
    }


}
