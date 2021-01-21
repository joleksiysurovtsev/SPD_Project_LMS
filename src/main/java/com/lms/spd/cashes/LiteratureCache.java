package com.lms.spd.cashes;

import com.lms.spd.models.interfaces.Literature;
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
    public static List<Literature> cashedLiteratureList = new CopyOnWriteArrayList<>();

    public void setLiteratureRepository(IRepository<Literature> literatureRepository) {
        this.literatureRepository = literatureRepository;
    }

    private IRepository<Literature> literatureRepository;


    public LiteratureCache(IRepository<Literature> literatureRepository) {
        this.literatureRepository = literatureRepository;
    }

    private LiteratureCache() {
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

    public Literature addLiteratire(Literature literature) {
        Literature returnedLiterature = literatureRepository.create(literature);
        cashedLiteratureList.add(returnedLiterature);
        return returnedLiterature;
    }

    public void removeLecturesByID(int lectureRemove) {
        cashedLiteratureList.removeIf(lecture -> lecture.getId() == lectureRemove);
        literatureRepository.delete(lectureRemove);
    }

    public List<Literature> getLiteraturesBYLectureID(int id) {
        return literatureRepository.literaturesBYLectureID(id);
    }

    public List<Literature> getCashedLiteratureList() {
        return cashedLiteratureList;
    }

    public void updateCashedLiteratures(){
        cashedLiteratureList = literatureRepository.readAll();
    }
}
