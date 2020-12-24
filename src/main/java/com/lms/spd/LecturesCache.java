package com.lms.spd;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The cache is implemented according to the <b><font size="4" color="green">Singleton principle</font></b>:
 * The Singleton pattern ensures that a class has only one instance and provides
 * a global access point to it.
 * <ol>
 * <li> There should be no more than one instance of a given class in the system.</li>
 * <li>The instance must be easily accessible to all clients of the given class.</li>
 * <li>Creation of an on demand object, that is, when it is needed for the first
 * time, and not during system initialization.</li>
 * </ol>
 * <a href="https://habr.com/ru/post/27108/">Singleton implementation</a>
 */
public class LecturesCache {

    private static volatile LecturesCache instance;
    private LectureServiceImpl lectureService = new LectureServiceImpl();
    List<Lecture> lectures = lectureService.getLectures();
    private Calendar currentDate;
    private static Map<Calendar, List<Lecture>> cash = new ConcurrentHashMap<>();

    private LecturesCache() {
        cashInit();
    }

    public static LecturesCache getInstance() {
        if (instance == null) {
            synchronized (LecturesCache.class) {
                if (instance == null) {
                    instance = new LecturesCache();
                }
            }
        }
        return instance;
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public List<Lecture> returnList() {
        return cash.get(currentDate);
    }

    /**
     * Method initializes cache
     * <ol>
     * <li>Go through the entire array of lectures</li>
     * <li>Take an element and check if there is a date key on the map</li>
     * <li>If there is no such key on the map, then generate a sheet with these dates and add it to the map </li>
     * </ol>
     */
    private void cashInit() {
        lectureService.getLectures().stream().map(Lecture::getLectureDate)
                .filter(date -> !cash.containsKey(date))
                .forEach(this::listLectureByDate);
    }

    /**
     *The method adds a lecture by date to the cache map
     * */
    private void listLectureByDate(Calendar date) {
        List<Lecture> lecturesAdd = lectures.stream().filter(lecture -> lecture.getLectureDate().equals(date)).collect(Collectors.toList()); //новый лист с лекциями и пихаем все с этими датами
        cash.put(date, lecturesAdd);
    }


    /**
     * The method adds a lecture to the map cache by key (date),
     * if there is no such key, it creates a key value and writes it to the cache
     */
    public static void updateCashAfterAdd(Lecture lectureAdded) {
        List<Lecture> listLectureWithCurrentDate = cash.containsKey(lectureAdded.getLectureDate()) ? cash.get(lectureAdded.getLectureDate()) : new ArrayList<>();
        listLectureWithCurrentDate.add(lectureAdded);
        cash.put(lectureAdded.getLectureDate(), listLectureWithCurrentDate);
    }

    public static void removeLectureFromCache(int[] lectureRemove, List<Lecture> lectures) {
        List<Lecture> listR = new ArrayList<>();
        Arrays.stream(lectureRemove).forEach(x -> lectures.stream().filter(lr -> lr.getId() == x).forEach(listR::add));
        LecturesCache.updateCashAfterRemove(listR);
    }

    private static void updateCashAfterRemove(List<Lecture> lectureDeleted) {
        lectureDeleted.forEach(lectures1 -> {
            List<Lecture> lsc = cash.get(lectures1.getLectureDate());
            lsc.removeIf(lecture -> lecture.getNameOfLecture().equals(lectures1.getNameOfLecture()));
            cash.put(lectures1.getLectureDate(), lsc);
        });
    }
}
