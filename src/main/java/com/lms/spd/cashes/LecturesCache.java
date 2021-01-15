package com.lms.spd.cashes;

import com.lms.spd.lmsjdbc.JDBCConnector;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.DBPostgresLectureRepository;
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

    private IRepository<Lecture> lectureRepository = new DBPostgresLectureRepository(JDBCConnector.connection);
    public List<Lecture> cashedLectureList = new CopyOnWriteArrayList<>();

    private LecturesCache() {
        cashInit();
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
        Lecture lecture1 = cashedLectureList.stream().filter(lecture -> lecture.getId() == selected).findFirst().orElse(null);
        if (lecture1 != null) {
            return lecture1;
        }
        return lectureRepository.getByID(selected);
    }

    public Lecture addLecture(Lecture lecture) {
        cashedLectureList.add(lecture);
        return lectureRepository.create(lecture);
    }

    public void removeLecturesByID(int lectureRemove) {
        cashedLectureList.removeIf(lecture -> lecture.getId() == lectureRemove);
        lectureRepository.delete(lectureRemove);
    }

    public void addLinkLiteratureLectures(int id, List<Integer> integers){
        lectureRepository.addIdMapToLiteratureToLeturesTable(id,integers);

    }

}
