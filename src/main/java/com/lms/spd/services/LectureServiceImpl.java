package com.lms.spd.services;



import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.AbstractLiterature;
import com.lms.spd.services.interfaces.LectureService;

import java.util.Arrays;
import java.util.Date;

public class LectureServiceImpl implements LectureService {

    private LectureIModel[] lectures = {
            new LectureIModel(1, "BufferedReader."),
            new LectureIModel(2, "Writes text to."),
            new LectureIModel(3, "Core Java API"),
            new LectureIModel(4, "Core Java API4"),
    };

    @Override
    public LectureIModel[] getLectures() {
        return lectures;
    }

    @Override
    public void setLectures(LectureIModel[] lectures) {
        this.lectures = lectures;
    }

    private LectureIModel selectedLecture;

    @Override
    public LectureIModel getSelectedLecture() {
        return selectedLecture;
    }

    @Override
    public void setSelectedLecture(int selected) {
        LectureIModel select = new LectureIModel("");
        for (LectureIModel lecture : lectures) {
            if (lecture.getNumberOfLecture() == selected + 1) {
                select = lecture;
                break;
            }
        }
        selectedLecture = select;
    }


//_________________________________________________________________________________________________________________//

    public void addLecture(LectureType type, int numberOfLec, String nameOfLecture, AbstractLiterature[] literatures, String lectorName, Date lectureDate) {
        int numberOfLecture = numberOfLec;

        LectureIModel[] arrayAddedLectures = new LectureIModel[lectures.length + 1];

        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);

        if (numberOfLecture > arrayAddedLectures.length) {
            numberOfLecture = arrayAddedLectures.length;
        }
        LectureIModel addedLecture = new LectureIModel(numberOfLecture, nameOfLecture, literatures,lectorName,lectureDate);
        addedLecture.setType(type);

        for (int i = arrayAddedLectures.length - 1; i > -1; i--) {
            if (i == numberOfLecture - 1) {
                arrayAddedLectures[i] = addedLecture;
                break;
            }
            arrayAddedLectures[i] = arrayAddedLectures[i - 1];
        }
        lectures = arrayAddedLectures;
        sortLectureArr();
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    @Override
    public boolean removeLectures(int lectureRemove) {
        boolean flag = false;
        for (LectureIModel value : lectures) {
            int numb = value.getNumberOfLecture();
            if (numb == lectureRemove) {
                lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLecture() == lectureRemove)).toArray(LectureIModel[]::new);
                sortLectureArrAfterRemove();
                flag = true;
                break;
            }
        }
        return flag;
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
            for (LectureIModel value : lectures) {
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
            lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLecture() == z)).toArray(LectureIModel[]::new);
        }
        sortLectureArrAfterRemove();
        return stringContains.toString();
    }

   //________________________________________________________________________________________________//

    private void sortLectureArr() {
        LectureIModel[] sortedArr = lectures;
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < lectures.length - 1; i++) {
                if (sortedArr[i].getNumberOfLecture() == sortedArr[i + 1].getNumberOfLecture()) {
                    sortedArr[i + 1].setNumberOfLecture((sortedArr[i + 1].getNumberOfLecture()) + 1);
                    flag = false;
                }
            }
        }
        lectures = sortedArr;
    }

    private void sortLectureArrAfterRemove() {
        LectureIModel[] sortedArr = lectures;
        for (int i = 0; i < lectures.length; i++) {
            sortedArr[i].setNumberOfLecture(i + 1);
        }
        lectures = sortedArr;
    }



}
