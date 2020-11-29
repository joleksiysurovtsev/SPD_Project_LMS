package com.lms.spd;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class LecturesCache {

    private LectureServiceImpl lectureService = new LectureServiceImpl();
    private List<Lecture> lectures = lectureService.getLectures();
    private Calendar currentDate ;
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
        //бежим по всему массиву основных лекций
        //берём первый элемент
        // проверяем есть ли ключ эта дата в мапе
        //если в мапе нет такого ключа то генерируем лист с єтими датами и пихаем
        lectures.stream().map(Lecture::getLectureDate).filter(date -> !cash.containsKey(date)).forEach(this::listLectureByDate);
    }

    private void listLectureByDate(Calendar date) {
        List<Lecture> lecturesAdd = lectures.stream().filter(lecture -> lecture.getLectureDate().equals(date)).collect(Collectors.toList()); //новый лист с лекциями и пихаем все с этими датами
        cash.put(date, lecturesAdd);
    }



    public static void updateCashAfterAdd(Lecture lectureAdded) {
        List<Lecture> listLectureWithCurrentDate;
        if (cash.containsKey(lectureAdded.getLectureDate())) {
            listLectureWithCurrentDate = cash.get(lectureAdded.getLectureDate()); //в лист лекций из кеша по дате
        } else {
            listLectureWithCurrentDate = new ArrayList<>();                  //или создаём если небыло
        }
        listLectureWithCurrentDate.add(lectureAdded);                             //добавляем лекцию
        cash.put(lectureAdded.getLectureDate(), listLectureWithCurrentDate);      //возвращаем в мапу
    }

    public static void removeLectCash(int[] lectureRemove, List<Lecture> lectures) {
        List<Lecture> listR = new ArrayList<>();
        Arrays.stream(lectureRemove).forEach(x -> lectures.stream().filter(lr -> lr.getId() == x).forEach(listR::add));
        LecturesCache.updateCashAfterRemove(listR);
    }

    private static void updateCashAfterRemove(List<Lecture> lectureDeleted) {
        lectureDeleted.forEach(lectures -> {
            List<Lecture> lsc = cash.get(lectures.getLectureDate());
            lsc.removeIf(lecture -> lecture.getNameOfLecture().equals(lectures.getNameOfLecture()));
            cash.put(lectures.getLectureDate(), lsc);
        });
    }
}
