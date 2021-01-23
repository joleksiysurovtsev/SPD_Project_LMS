package com.lms.spd.cashes.mocks;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.DBLectureRepository;

import java.util.*;

public class DBLectureRepositoryMock extends DBLectureRepository {

    private static List<Lecture> lectureTestList = initLectLists();

    public static List<Lecture> getLectureTestList() {
        return lectureTestList;
    }

    private static List<Lecture> initLectLists() {
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        List<Lecture> lectureTestList = new ArrayList<>();
        lectureTestList.add(new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar, 1));
        lectureTestList.add(new LectureIModel(LectureType.COMMON, "nameOfLecture2", literatures, "lectorName2", calendar, 2));
        lectureTestList.add(new LectureIModel(LectureType.DB, "nameOfLecture3", literatures, "lectorName4", calendar, 3));
        return lectureTestList;
    }

    public void updates(){
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        List<Lecture> lectureList = new ArrayList<>();
        lectureList.add(new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar, 1));
        lectureList.add(new LectureIModel(LectureType.COMMON, "nameOfLecture2", literatures, "lectorName2", calendar, 2));
        lectureList.add(new LectureIModel(LectureType.DB, "nameOfLecture3", literatures, "lectorName4", calendar, 3));
        lectureTestList = lectureList;
    }

    @Override
    public List<Lecture> readAll() {
        return lectureTestList;
    }

    @Override
    public Lecture getByID(int id) {
        Optional<Lecture> lecture1 = lectureTestList.stream().filter(lecture -> lecture.getId() == id).findFirst();
        return lecture1.orElse(new LectureIModel());
    }

    @Override
    public Lecture create(Lecture item) {
        lectureTestList.add(item);
        item.setId(4);
        return item;
    }

    @Override
    public int addIdMapToLiteratureToLeturesTable(int id, Integer literatureList) {
        return -1;
    }

    @Override
    public boolean update(Lecture item) {
        if(lectureTestList.stream().noneMatch(lecture -> lecture.getId()== item.getId())){
            Optional<Lecture> first = lectureTestList.stream().filter(lecture -> lecture.getId() == item.getId()).findFirst();
            int i = lectureTestList.indexOf(first.get());
            lectureTestList.set(i,item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return lectureTestList.removeIf(lecture -> lecture.getId() == id);
    }

    @Override
    public boolean deleteFromLitToLectures(int id) {
        return super.deleteFromLitToLectures(id);
    }

    @Override
    public List<Lecture> literaturesBYLectureID(int id) {
        return super.literaturesBYLectureID(id);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
