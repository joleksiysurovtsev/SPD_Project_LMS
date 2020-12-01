package com.lms.spd.services;

import com.lms.spd.LecturesCache;
import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.LectureRepository;
import com.lms.spd.services.interfaces.LectureService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LectureServiceImpl implements LectureService {

    private LectureRepository repository;

    public LectureServiceImpl() {
        this.repository = new LectureRepository();
    }

    private Lecture selectedLecture;

    @Override
    public List<Lecture> getLectures() {
        return repository.getAll();
    }

    @Override
    public void setLectures(List<Lecture> lectures) {
        repository.setAll(lectures);
    }

    @Override
    public Lecture getSelectedLecture() {
        return selectedLecture;
    }

    @Override
    public void setSelectedLecture(int selected) {
        selectedLecture = repository.getAll().stream().filter(lecture -> lecture.getId() == selected).findFirst().orElse(null);
        if (selectedLecture == null) {
            try {
                throw new NullLectureException("There is no lecture under this number");
            } catch (NullLectureException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @Override
    public void addLecture(Lecture lecture) {
        lecture.setId(generateLectureID(repository.getAll()));
        List<Lecture> addList = repository.getAll();
        addList.add(lecture);
        repository.setAll(addList.stream().sorted(Comparator.comparing(Lecture::getLectureDate)).collect(Collectors.toList()));
        LecturesCache.updateCashAfterAdd(lecture);
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public void removeLectures(int[] lectureRemove) {
        LecturesCache.removeLectCash(lectureRemove, repository.getAll());
        List<Lecture> lectures = repository.getAll();
        for (int s : lectureRemove) {
            lectures = lectures.stream().filter(lecture -> lecture.getId() != s).collect(Collectors.toList());
        }
        repository.setAll(lectures);
    }

    //________________________________________________________________________________________________//

    public static int generateLectureID(List<Lecture> lectures) {
        return (lectures.stream().map(Lecture::getId).max(Integer::compareTo).orElse(0)) +1;
    }
}
