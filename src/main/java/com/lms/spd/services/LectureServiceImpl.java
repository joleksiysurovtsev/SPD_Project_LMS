package com.lms.spd.services;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.interfaces.LectureService;

import java.util.*;
import java.util.stream.IntStream;

public class LectureServiceImpl implements LectureService {

    private List<Lecture> lectures = new ArrayList<>();

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

    public void addLecture(Lecture lecture) {
        if ((lectures.isEmpty() || lecture.getNumberOfLecture() > lectures.size())) {
            lectures.add(lecture);
        } else {
            lectures.add(lecture.getNumberOfLecture() - 1, lecture);
        }
        sortLectureArr();
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public boolean removeLectures(int lectureRemove) {
        boolean removedLect = lectures.removeIf(lectures -> lectures.getNumberOfLecture() == lectureRemove);
        sortLectureArrAfterRemove();
        return removedLect;
    }


    @Override
    public void removeLectures(String[] lectureRemove) {
        Arrays.stream(lectureRemove).mapToInt(Integer::parseInt).forEach(z -> lectures.removeIf(p -> p.getNumberOfLecture() == z));
        sortLectureArrAfterRemove();
    }


    //________________________________________________________________________________________________//

    private void sortLectureArr() {
        IntStream.range(0, lectures.size() - 1).filter(i -> lectures.get(i).getNumberOfLecture() == lectures.get(i + 1)
                .getNumberOfLecture()).forEach(i -> lectures.get(i + 1).setNumberOfLecture((lectures.get(i + 1)
                .getNumberOfLecture()) + 1));
    }


    private void sortLectureArrAfterRemove() {
        IntStream.range(0, lectures.size()).forEach(i -> lectures.get(i).setNumberOfLecture(i + 1));
    }
}
