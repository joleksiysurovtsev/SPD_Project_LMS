package com.lms.spd.services;
import com.lms.spd.LecturesCash;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.LecturesRepository;
import com.lms.spd.services.interfaces.LectureService;

import java.util.*;
import java.util.stream.IntStream;

public class LectureServiceImpl implements LectureService {

    private LecturesRepository repository;

    public LectureServiceImpl() {
        this.repository = new LecturesRepository();
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
        if ((repository.getAll().isEmpty() || lecture.getNumberOfLecture() > repository.getAll().size())) {
            repository.getAll().add(lecture);
        } else {
            repository.getAll().add(lecture.getNumberOfLecture() - 1, lecture);
        }
        sortByDate();
        LecturesCash.updateCashAfterAdd(lecture);
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public void removeLectures(String[] lectureRemove) {
        LecturesCash.removeLectCash(lectureRemove, repository.getAll());
        Arrays.stream(lectureRemove).mapToInt(Integer::parseInt).forEach(z -> repository.getAll().removeIf(p -> p.getId() == z));
    }


    //________________________________________________________________________________________________//

    private void sortByDate() {
        repository.getAll().sort(Comparator.comparing(Lecture::getLectureDate));
    }

    @Deprecated
    private void sortLectureArrAfterRemove() {
        IntStream.range(0, repository.getAll().size()).forEach(i -> repository.getAll().get(i).setNumberOfLecture(i + 1));
    }
}
