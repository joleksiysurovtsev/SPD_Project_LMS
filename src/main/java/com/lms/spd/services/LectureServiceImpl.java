package com.lms.spd.services;


import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LectureService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class LectureServiceImpl implements LectureService {


    private ArrayList<Lecture> lectures = new ArrayList<>();

    @Override
    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    @Override
    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    private Lecture selectedLecture;

    @Override
    public Lecture getSelectedLecture() {
        return selectedLecture;
    }

    @Override
    public void setSelectedLecture(int selected) {
        Lecture select = new LectureIModel("");
        for (Lecture lecture : lectures) {
            if (lecture.getNumberOfLecture() == selected + 1) {
                select = lecture;
                break;
            }
        }
        selectedLecture = select;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public void addLecture(LectureType type, int numberOfLec, String nameOfLecture, ArrayList<Literature> literatures, String lectorName, Date lectureDate) {
        Lecture addedLecture = new LectureIModel(type, numberOfLec, nameOfLecture, literatures, lectorName, lectureDate);
        if (lectures.size() == 0 || numberOfLec > lectures.size()) {
            lectures.add(addedLecture);
        } else {
            lectures.add(numberOfLec - 1, addedLecture);
        }
        sortLectureArr();
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public boolean removeLectures(int lectureRemove) {
        if (lectures.size() == 1 || lectureRemove == 1) {
            lectures = new ArrayList<>();
            return true;
        }
        if (!(lectureRemove < 0 || lectureRemove >= lectures.size())) {
            lectures.remove(lectureRemove - 1);
            return true;
        }
        return false;
    }

    @Override
    public String removeLectures(String lectureRemove) {
        String[] strings = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа]*", "");
        }
        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : lectures) {
                int numb = value.getNumberOfLecture();
                if (numb == Integer.parseInt(item)) {
                    flag = false;
                    stringContains.append(" ").append(item).append(" ");
                    break;
                }
            }
        }
        if (!flag) {
            stringContains.append("successfully removed the rest are missing.");
        } else {
            stringContains.append("are missing.");
        }
        for (String s : numbToDisplay) {
            int z = Integer.parseInt(s);
            lectures.removeIf(p -> p.getNumberOfLecture() == z);
        }
        sortLectureArrAfterRemove();
        return stringContains.toString();
    }

    //________________________________________________________________________________________________//

    private void sortLectureArr() {
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < lectures.size() - 1; i++) {
                if (lectures.get(i).getNumberOfLecture() == lectures.get(i + 1).getNumberOfLecture()) {
                    lectures.get(i + 1).setNumberOfLecture((lectures.get(i + 1).getNumberOfLecture()) + 1);
                    flag = false;
                }
            }
        }
    }

    private void sortLectureArrAfterRemove() {
        for (int i = 0; i < lectures.size(); i++) {
            lectures.get(i).setNumberOfLecture(i + 1);
        }
    }
}
