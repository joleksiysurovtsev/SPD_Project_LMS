package com.lms.spd.cashes;

import com.lms.spd.lmsjdbc.JDBCConnector;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.DBPostgresLiteratureRepository;
import com.lms.spd.repository.interfaces.IRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * The cache is implemented according to the Singelton pattern.
 * The Singleton pattern ensures that a class has only one
 * instance and provides a global access point to it.
 */
public class LiteratureCache {

    private static volatile LiteratureCache instance;

    private IRepository<Literature> literatureRepository = new DBPostgresLiteratureRepository(JDBCConnector.connection);
    public List<Literature> cashedLiteratureList = new CopyOnWriteArrayList<>();

    private LiteratureCache() {
        cashInit();
    }

    public static LiteratureCache getInstance() {
        LiteratureCache localInstance = instance;
        if (localInstance == null) {
            synchronized (LiteratureCache.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LiteratureCache();
                }
            }
        }
        return localInstance;
    }


    private void cashInit() {
        cashedLiteratureList = literatureRepository.readAll();
    }


    public Literature getByID(int selected) {
        return cashedLiteratureList.get(selected);
    }

    public Literature addLiteratire(Literature lecture) {
        cashedLiteratureList.add(lecture);
        return literatureRepository.create(lecture);
    }

    public void removeLecturesByID(int lectureRemove) {
        cashedLiteratureList.removeIf(lecture -> lecture.getId() == lectureRemove);
        literatureRepository.delete(lectureRemove);
    }



}
