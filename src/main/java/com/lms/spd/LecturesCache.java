package com.lms.spd;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import java.util.*;

public class LecturesCache {

    private LectureServiceImpl lectureService = new LectureServiceImpl();
    private List<Lecture> lectures = lectureService.getLectures();
    private Calendar curentDate = new GregorianCalendar(2020, Calendar.OCTOBER, 5);
    private Map<Calendar, List<Lecture>> cash = new HashMap();

    public Calendar getCurentDate() {
        return curentDate;
    }

    public void setCurentDate(Calendar curentDate) {
        this.curentDate = curentDate;
    }

    public LecturesCache() {
        cashInit();
    }

    public List<Lecture> returnList() {
        return cash.get(curentDate) == null ? new ArrayList<>() : cash.get(curentDate);
    }

    /**
     * method initializes cache
     */
    private void cashInit() {
        for (int i = 0; i < lectures.size(); i++) {   //бежим по всему массиву основных лекций
            Lecture lect = lectures.get(i);           //берём первый элемент
            // проверяем есть ли ключ эта дата в мапе
            Calendar date = lect.getLectureDate();
            if (!cash.containsKey(date)) { //если в мапе нет такого ключа то генерируем лист с єтими датами и пихаем
                List<Lecture> lecturesadded = new ArrayList<>(); //новый лист с лекциями и пихаем все с этими датами
                for (Lecture lecture : lectures) {
                    if (lecture.getLectureDate().equals(date)) {
                        lecturesadded.add(lecture);
                    }
                }
                cash.put(date, lecturesadded);
            }
        }
    }



    public void updateCashAfterAdd(Lecture lectureAdded) {
        List<Lecture> lsc;
        if (cash.containsKey(lectureAdded.getLectureDate())) {
            lsc = cash.get(lectureAdded.getLectureDate()); //в лист лекций из кеша по дате
        } else {
            lsc = new ArrayList<>();                  //или создаём если небыло
        }
        lsc.add(lectureAdded);                             //добавляем лекцию
        cash.put(lectureAdded.getLectureDate(), lsc);      //возвращаем в мапу
    }

    public void updateCashAfterRemove(List<Lecture> lectureDeleted) {
        lectureDeleted.forEach(lectures -> {
            List<Lecture> lsc = cash.get(lectures.getLectureDate());
            lsc.removeIf(lecture -> lecture.getNameOfLecture().equals(lectures.getNameOfLecture()));
            cash.put(lectures.getLectureDate(), lsc);
        });
    }
}