package com.lms.spd.cashes;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.interfaces.IRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * The cache is implemented according to the Singelton pattern.
 * The Singleton pattern ensures that a class has only one
 * instance and provides a global access point to it.
 */
public class LecturesCache {

    private static volatile LecturesCache instance;
    private static List<Lecture> cashedLectureList = new CopyOnWriteArrayList<>();
    private static IRepository<Lecture> lectureRepository;


    public void setLectureRepository(IRepository<Lecture> lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public LecturesCache(IRepository<Lecture> lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    private LecturesCache() {

    }

    public static LecturesCache getInstance() {
        LecturesCache localInstance = instance;
        if (localInstance == null) {
            synchronized (LecturesCache.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LecturesCache();
                }
            }
        }
        return localInstance;
    }

    private void cashInit() {
        cashedLectureList = lectureRepository.readAll();
    }

    public Lecture getByID(int selected) {
        return cashedLectureList.stream().filter(lecture -> lecture.getId() == selected).findFirst().orElseGet(() -> lectureRepository.getByID(selected));
    }

    public Lecture addLecture(Lecture lecture) {
        Lecture returnedLecture = lectureRepository.create(lecture);
        cashedLectureList.add(returnedLecture);
        return returnedLecture;
    }

    public boolean removeLecturesByID(int lectureRemove) {
        cashedLectureList.removeIf(lecture -> lecture.getId() == lectureRemove);
        return lectureRepository.delete(lectureRemove);
    }

    public void addLinkLiteratureLectures(int id, Integer integers) {
        lectureRepository.addIdMapToLiteratureToLeturesTable(id, integers);
    }
    public boolean update(Lecture lectureUpdate) {
        cashedLectureList.forEach(lecture -> {
            if (lecture.getId() == lectureUpdate.getId()){
                lecture = lectureUpdate;
            }
        });

        return lectureRepository.update(lectureUpdate);
    }

    public List<Lecture> getCashedLectureList() {
        return cashedLectureList;
    }
    
    public void updateCashedLectures(){
        cashedLectureList = lectureRepository.readAll();
    }
}
