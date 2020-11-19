package com.lms.spd.services;

import com.lms.spd.LecturesCache;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.LectureRepository;
import com.lms.spd.services.interfaces.LectureService;

import java.util.*;

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
        selectedLecture = repository.getAll().stream().filter(lecture -> lecture.getNumberOfLecture() == selected + 1).findFirst().orElse(new LectureIModel(""));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @Override
    public void addLecture(Lecture lecture) {
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


}
