package com.lms.spd.services;

import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.IService;

import java.util.Arrays;
import java.util.List;

public class LiteratureServiceImpl implements IService<Literature> {

    private Literature selectedLiterature;

    /*✅*/
    @Override
    public List<Literature> getItems() {
        return LiteratureCache.getInstance().getCashedLiteratureList();
    }

    /*✅*/
    @Override
    public Literature getSelectedItem() {
        return selectedLiterature;
    }

    /*✅*/
    @Override
    public void setSelectedItem(int selected) {
        selectedLiterature = LiteratureCache.getInstance().getByID(selected);
    }

    /*✅*/
    @Override
    public Literature addItem(Literature literature) {
        return LiteratureCache.getInstance().addLiteratire(literature);
    }

    /*✅*/
    @Override
    public boolean removeItems(int[] lectureRemove) {
        return Arrays.stream(lectureRemove).anyMatch(id -> LiteratureCache.getInstance().removeLecturesByID(id));
    }

    /*✅*/
    public List<Literature> getLiteraturesBYLectureID(int id) {
        return LiteratureCache.getInstance().getLiteraturesBYLectureID(id);
    }

    public Literature getByID(int id) {
        return LiteratureCache.getInstance().getByID(id);
    }
}
