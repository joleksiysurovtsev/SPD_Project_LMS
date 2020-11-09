package com.lms.spd.services;

import com.lms.spd.LMSTerminal;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.interfaces.LectureService;

import java.util.*;
import java.util.stream.IntStream;

public class LectureServiceImpl implements LectureService {

    private List<Lecture> lectures = new ArrayList<>() {
        {
            add(new LectureIModel(LectureType.JAVA_CORE, 1, "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5)));
            add(new LectureIModel(LectureType.COMMON, 2, "\"Intellij IDEA Features. GitLab flow.\"", new ArrayList<>(), "Andrii Zaiats", new GregorianCalendar(2020, 9, 5)));
            add(new LectureIModel(LectureType.JAVA_CORE, 3, "\"JAVA_CORE Java API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 9, 12)));
            add(new LectureIModel(LectureType.COMMON, 4, "\"Debugging. Build Tools: Graddle. Unit-testing basics\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 9, 14)));
            add(new LectureIModel(LectureType.JAVA_CORE, 5, "\"Class design\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 9, 19)));
            add(new LectureIModel(LectureType.COMMON, 6, "\"Programming Principles: DRY, KISS, YAGNI. Refactoring.\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 9, 21)));
            add(new LectureIModel(LectureType.JAVA_CORE, 7, "\"Enums. Generics\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 9, 26)));
            add(new LectureIModel(LectureType.COMMON, 8, "\"Refactoring techniques\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 9, 28)));
            add(new LectureIModel(LectureType.JAVA_CORE, 9, "\"Collection API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 10, 2)));
            add(new LectureIModel(LectureType.COMMON, 10, "\"TDD\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 10, 4)));
            add(new LectureIModel(LectureType.JAVA_CORE, 11, "\"Exceptions\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 10, 9)));
            add(new LectureIModel(LectureType.COMMON, 12, "\"SOLID\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 10, 11)));
            add(new LectureIModel(LectureType.JAVA_CORE, 13, "\"Functional programming basics\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 10, 16)));
            add(new LectureIModel(LectureType.JAVA_CORE, 14, "\"Stream API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 10, 18)));
            add(new LectureIModel(LectureType.JAVA_CORE, 15, "\"IO & File API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 10, 23)));
            add(new LectureIModel(LectureType.COMMON, 16, "\"Design patterns & Antipatterns\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2020, 10, 26)));
            add(new LectureIModel(LectureType.JAVA_CONCURRENCY, 17, "\"Threads. Executors\"", new ArrayList<>(), "	Mykola Popadyk	", new GregorianCalendar(2020, 10, 30)));
            add(new LectureIModel(LectureType.DB, 18, "\"SQL\"", new ArrayList<>(), "	Ruslan Pistriak	", new GregorianCalendar(2020, 11, 2)));
            add(new LectureIModel(LectureType.JAVA_CONCURRENCY, 19, "\"Concurrency (part 1)\"", new ArrayList<>(), "	Mykola Popadyk	", new GregorianCalendar(2020, 11, 7)));
            add(new LectureIModel(LectureType.DB, 20, "\"Database migrations: Flyway\"", new ArrayList<>(), "	Ruslan Pistriak	", new GregorianCalendar(2020, 11, 9)));
            add(new LectureIModel(LectureType.JAVA_CONCURRENCY, 21, "\"Concurrency (part 2)\"", new ArrayList<>(), "	Mykola Popadyk	", new GregorianCalendar(2020, 11, 14)));
            add(new LectureIModel(LectureType.DB, 22, "\"JDBC\"", new ArrayList<>(), "	Ruslan Pistriak	", new GregorianCalendar(2020, 11, 16)));
            add(new LectureIModel(LectureType.JAVA_CONCURRENCY, 23, "\"Concurrency (part 3)\"", new ArrayList<>(), "	Mykola Popadyk	", new GregorianCalendar(2020, 11, 21)));
            add(new LectureIModel(LectureType.JAVA_CORE, 24, "\"Reflection API\"", new ArrayList<>(), "	Vova Shevchenko	", new GregorianCalendar(2020, 11, 23)));
            add(new LectureIModel(LectureType.EE, 25, "\"Servlet API (part 1)\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 0, 11)));
            add(new LectureIModel(LectureType.COMMON, 26, "\"Modern Software Architecture\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2021, 0, 13)));
            add(new LectureIModel(LectureType.EE, 27, "\"Servlet API (part 2)\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 0, 18)));
            add(new LectureIModel(LectureType.COMMON, 28, "\"REST, Swagger, Open API\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2021, 0, 20)));
            add(new LectureIModel(LectureType.EE, 29, "\"Spring overview. Spring DI.\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 0, 25)));
            add(new LectureIModel(LectureType.DB, 30, "\"Java Persistence: Hibernate\"", new ArrayList<>(), "	Ruslan Pistriak	", new GregorianCalendar(2021, 0, 27)));
            add(new LectureIModel(LectureType.EE, 31, "\"Spring MVC. Thymeleaf.\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 1, 1)));
            add(new LectureIModel(LectureType.DB, 32, "\"Java Persistence: Spring Data JPA\"", new ArrayList<>(), "	Ruslan Pistriak	", new GregorianCalendar(2021, 1, 3)));
            add(new LectureIModel(LectureType.EE, 34, "\"Spring in depth.\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 1, 8)));
            add(new LectureIModel(LectureType.COMMON, 35, "\"Code quality\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2021, 1, 10)));
            add(new LectureIModel(LectureType.EE, 36, "\"Spring Security. (part 1)\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 1, 15)));
            add(new LectureIModel(LectureType.COMMON, 37, "\"Deploy (AWS). CI/CD\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2021, 1, 17)));
            add(new LectureIModel(LectureType.EE, 38, "\"Spring Security. (part 2)\"", new ArrayList<>(), "	Vasya Rudas	", new GregorianCalendar(2021, 1, 22)));
            add(new LectureIModel(LectureType.COMMON, 39, "\"Deploy (AWS). CI/CD - practice\"", new ArrayList<>(), "	Andrii Zaiats	", new GregorianCalendar(2021, 1, 24)));
        }
    };

    private Lecture selectedLecture;

    @Override
    public List<Lecture> getLectures() {
        return lectures;
    }

    @Override
    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public Lecture getSelectedLecture() {
        return selectedLecture;
    }

    @Override
    public void setSelectedLecture(int selected) {
        selectedLecture = lectures.stream().filter(lecture -> lecture.getNumberOfLecture() == selected + 1).findFirst().orElse(new LectureIModel(""));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @Override
    public void addLecture(Lecture lecture) {
        if ((lectures.isEmpty() || lecture.getNumberOfLecture() > lectures.size())) {
            lectures.add(lecture);
        } else {
            lectures.add(lecture.getNumberOfLecture() - 1, lecture);
        }
        LMSTerminal.cash.updateCashAfterAdd(lecture);
        sortByDate();
        sortLectureArr(); //нумерует лекции в порядке возрастания
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public void removeLectures(String[] lectureRemove) {
        removeLectCash(lectureRemove);
        Arrays.stream(lectureRemove).mapToInt(Integer::parseInt).forEach(z -> lectures.removeIf(p -> p.getNumberOfLecture() == z));
        sortLectureArrAfterRemove();
    }

    private void removeLectCash(String[] lectureRemove) {
        List<Lecture> listR = new ArrayList<>();
        Arrays.stream(lectureRemove).mapToInt(Integer::parseInt).forEach(x -> lectures.stream().filter(lr -> lr.getNumberOfLecture() == x).forEach(listR::add));
        LMSTerminal.cash.updateCashAfterRemove(listR);
    }

    //________________________________________________________________________________________________//

    private void sortByDate() {
        lectures.sort(Comparator.comparing(Lecture::getLectureDate));
    }
    private void sortLectureArr() {
        IntStream.range(0, lectures.size() - 1).filter(i -> lectures.get(i).getNumberOfLecture() == lectures.get(i + 1)
                .getNumberOfLecture()).forEach(i -> lectures.get(i + 1).setNumberOfLecture((lectures.get(i + 1)
                .getNumberOfLecture()) + 1));
    }

    private void sortLectureArrAfterRemove() {
        IntStream.range(0, lectures.size()).forEach(i -> lectures.get(i).setNumberOfLecture(i + 1));
    }
}
