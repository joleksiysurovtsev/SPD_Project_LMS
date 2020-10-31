package com.lms.spd.services;


import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LectureService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

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
        selectedLecture = lectures.stream().filter(lecture -> lecture.getNumberOfLecture() == selected + 1).findFirst().orElse(new LectureIModel(""));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public void addLecture(LectureType type, int numberOfLec, String nameOfLecture, ArrayList<Literature> literatures, String lectorName, Date lectureDate) {
        Lecture addedLecture = new LectureIModel(type, numberOfLec, nameOfLecture, literatures, lectorName, lectureDate);
        if ((lectures.isEmpty() || numberOfLec > lectures.size())) {
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
        } else if (!(lectureRemove < 0 || lectureRemove >= lectures.size())) {
            lectures.remove(lectureRemove - 1);
            sortLectureArrAfterRemove();
            return true;
        }
        return false;

    }

    @Override
    public String removeLectures(String lectureRemove) {
        String[] strings = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, strings.length).forEach(i -> strings[i] = strings[i].replaceAll("[a-zA-Zа]*", ""));
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
        stringContains.append(!flag ? "successfully removed the rest are missing." : "are missing.");
        Arrays.stream(numbToDisplay).mapToInt(Integer::parseInt).forEach(z -> lectures.removeIf(p -> p.getNumberOfLecture() == z));
        sortLectureArrAfterRemove();
        return stringContains.toString();
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
