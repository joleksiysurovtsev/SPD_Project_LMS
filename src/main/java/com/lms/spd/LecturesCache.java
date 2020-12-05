package com.lms.spd;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class LecturesCache {

    private LectureServiceImpl lectureService = new LectureServiceImpl();
    private List<Lecture> lectures = lectureService.getLectures();
    private Calendar currentDate;
    private static Map<Calendar, List<Lecture>> cash = new HashMap();

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public LecturesCache() {
        cashInit();
    }

    public List<Lecture> returnList() {
        return cash.get(currentDate);
    }

    /**
     * method initializes cache
     */
    private void cashInit() {
        // run through the entire array of main lectures
        // take the first element
        // check if there is a key for this date in the map
        // if there is no such key in the map then generate a sheet with these dates and shove
        lectures.stream().map(Lecture::getLectureDate).filter(date -> !cash.containsKey(date)).forEach(this::listLectureByDate);
    }

    private void listLectureByDate(Calendar date) {
        List<Lecture> lecturesAdd = lectures.stream().filter(lecture -> lecture.getLectureDate().equals(date)).collect(Collectors.toList()); //новый лист с лекциями и пихаем все с этими датами
        cash.put(date, lecturesAdd);
    }


    /**
     * the method adds a lecture to the map cache by key (date),
     * if there is no such key, it creates a key value and writes it to the cache
     */
    public static void updateCashAfterAdd(Lecture lectureAdded) {
        List<Lecture> listLectureWithCurrentDate = cash.containsKey(lectureAdded.getLectureDate()) ? cash.get(lectureAdded.getLectureDate()) : new ArrayList<>();
        listLectureWithCurrentDate.add(lectureAdded);
        cash.put(lectureAdded.getLectureDate(), listLectureWithCurrentDate);
    }


    public static void removeLectureFromCache(int[] lectureRemove, List<Lecture> lectures) {
        //
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
