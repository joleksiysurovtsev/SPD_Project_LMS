package com.lms.spd.services;

import com.lms.spd.LecturesCache;
import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.LectureRepository;
import com.lms.spd.services.interfaces.LectureService;

import java.util.*;

public class LectureServiceImpl implements LectureService {

    private final LectureRepository repository;

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
    public void setSelectedLecture(int selected) throws NullLectureException {
        selectedLecture = repository.getAll().stream().filter(lecture -> lecture.getNumberOfLecture() == selected + 1).findFirst().orElse(null);
        if (selectedLecture == null){
            throw new NullLectureException("There is no lecture under this number");
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @Override
    public void addLecture(Lecture lecture) {
        lecture.setId(generateLectureID(repository.getAll()));
        repository.addLecture(lecture);
        sortByDate();
        LecturesCache.updateCashAfterAdd(lecture);
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public void removeLectures(String[] lectureRemove) {
        LecturesCache.removeLectCash(lectureRemove, repository.getAll());
        Arrays.stream(lectureRemove).mapToInt(Integer::parseInt).forEach(z -> repository.removeLecture(z));
    }

    //________________________________________________________________________________________________//

    private void sortByDate() {
        repository.getAll().sort(Comparator.comparing(Lecture::getLectureDate));
    }


    public static int generateLectureID(List<Lecture> lectures) {
        return (lectures.stream().map(Lecture::getId).max(Integer::compareTo).orElse(0))+1;
    }
}
