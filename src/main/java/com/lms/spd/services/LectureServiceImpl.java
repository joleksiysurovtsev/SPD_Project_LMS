package com.lms.spd.services;

import com.lms.spd.utils.LectureCollectorByType;
import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.enums.LectureType;
import com.lms.spd.lmsjdbc.JDBCConnector;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.DBPostgresLectureRepository;
import com.lms.spd.services.interfaces.IService;

import java.util.*;
import java.util.stream.Collectors;

public class LectureServiceImpl implements IService<Lecture> {

    private Lecture selectedLecture;

    /*✅*/
    @Override
    public List<Lecture> getItems() {
        return LecturesCache.getInstance().cashedLectureList;
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
        if (selectedLecture == null) {
            selectedLecture = new DBPostgresLectureRepository(JDBCConnector.connection).getByID(selected);
        }

    }

    /*✅*/
    @Override
    public Lecture addItem(Lecture lecture) {
        return LecturesCache.getInstance().addLecture(lecture);
    }

    @Override
    public void removeItems(int[] lectureRemove) {
        Arrays.stream(lectureRemove).forEach(id -> LecturesCache.getInstance().removeLecturesByID(id));
    }

    public List<Lecture> getLectureListByType(LectureType lectureType) {
        Map<LectureType, List<Lecture>> collect = LecturesCache.getInstance().cashedLectureList.stream().collect(LectureCollectorByType.collectToSortedMapByType());
        return collect.get(lectureType);
    }

    public List<Lecture> getLectureListByDate(Calendar enterTheDate) {
        return LecturesCache.getInstance().cashedLectureList.stream().filter(lecture -> lecture.getLectureDate().getTime().compareTo(enterTheDate.getTime()) == 0).collect(Collectors.toList());
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
        LecturesCache.getInstance().addLinkLiteratureLectures(id,integers);
    }
}
