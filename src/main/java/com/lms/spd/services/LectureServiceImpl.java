package com.lms.spd.services;

import com.lms.spd.utils.LectureCollectorByType;
import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.interfaces.IService;

import java.util.*;
import java.util.stream.Collectors;

public class LectureServiceImpl implements IService<Lecture> {

    private Lecture selectedLecture;


    /*✅*/
    @Override
    public List<Lecture> getItems() {
        return LecturesCache.getInstance().getCashedLectureList();
    }

    /*✅*/
    @Override
    public Lecture getSelectedItem() {
        return selectedLecture;
    }

    /*✅*/
    @Override
    public void setSelectedItem(int selected) {
        selectedLecture = LecturesCache.getInstance().getByID(selected);
    }

    /*✅*/
    @Override
    public Lecture addItem(Lecture lecture) {
        return LecturesCache.getInstance().addLecture(lecture);
    }

    @Override
    public boolean removeItems(int[] lectureRemove) {

        return Arrays.stream(lectureRemove).anyMatch(id -> LecturesCache.getInstance().removeLecturesByID(id));
    }

    public List<Lecture> getLectureListByType(LectureType lectureType) {
        Map<LectureType, List<Lecture>> collect = LecturesCache.getInstance().getCashedLectureList().stream().collect(LectureCollectorByType.collectToSortedMapByType());
        if (!collect.containsKey(lectureType)) {
            return new ArrayList<>();
        }
        return collect.get(lectureType);
    }

    public List<Lecture> getLectureListByDate(Calendar enterTheDate) {
        return LecturesCache.getInstance().getCashedLectureList().stream()
                .filter(lecture -> (lecture.getLectureDate().get(Calendar.YEAR) == enterTheDate.get(Calendar.YEAR) &&
                        lecture.getLectureDate().get(Calendar.DAY_OF_YEAR) == enterTheDate.get(Calendar.DAY_OF_YEAR))
                ).collect(Collectors.toList());
    }

    public List<Lecture> getLecturesByNumber(int[] getLectures) {
        List<Lecture> lectures = new ArrayList<>();
        Arrays.stream(getLectures).forEach(id -> lectures.add(LecturesCache.getInstance().getByID(id)));
        return lectures;
    }

    public List<Lecture> getLectureListByTypeAndDate(LectureType selectLectureType, Calendar enterTheDate) {
        List<Lecture> lectureListByType = getLectureListByType(selectLectureType);
        List<Lecture> lectureListByDate = getLectureListByDate(enterTheDate);
        return lectureListByType.stream().filter(lectureListByDate::contains).collect(Collectors.toList());
    }


    public void addLinkLiteratureLectures(int id, List<Integer> integers) {
        integers.forEach(integer -> LecturesCache.getInstance().addLinkLiteratureLectures(id, integer));
    }

    public boolean updateLecture(Lecture updateLecture) {
        return LecturesCache.getInstance().update(updateLecture);
    }
}
