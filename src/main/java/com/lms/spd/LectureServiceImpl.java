package com.lms.spd;



import com.lms.spd.interfaces.LectureService;

import java.util.Arrays;
import java.util.Date;

public class LectureServiceImpl implements LectureService {

    private LectureImpl[] lectures = {
            new LectureImpl(1, "BufferedReader."),
            new LectureImpl(2, "Writes text to."),
            new LectureImpl(3, "Core Java API"),
            new LectureImpl(4, "Core Java API4"),
    };

    @Override
    public LectureImpl[] getLectures() {
        return lectures;
    }

    @Override
    public void setLectures(LectureImpl[] lectures) {
        this.lectures = lectures;
    }

    private LectureImpl selectedLecture;

    @Override
    public LectureImpl getSelectedLecture() {
        return selectedLecture;
    }

    @Override
    public void setSelectedLecture(int selected) {
        LectureImpl select = new LectureImpl("");
        for (LectureImpl lecture : lectures) {
            if (lecture.getNumberOfLecture() == selected + 1) {
                select = lecture;
                break;
            }
        }
        selectedLecture = select;
    }


//_________________________________________________________________________________________________________________//

    /**
     * Adds a new lecture by only name, to an array
     */

    public void addLecture(int numberOfLec, String nameOfLecture, Literature[] literatures, String lectorName, Date lectureDate) {
        int numberOfLecture = numberOfLec;

        LectureImpl[] arrayAddedLectures = new LectureImpl[lectures.length + 1];

        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);

        if (numberOfLecture > arrayAddedLectures.length) {
            numberOfLecture = arrayAddedLectures.length;
        }
        LectureImpl addedLecture = new LectureImpl(numberOfLecture, nameOfLecture, literatures,lectorName,lectureDate);

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
        for (LectureImpl value : lectures) {
            int numb = value.getNumberOfLecture();
            if (numb == lectureRemove) {
                lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLecture() == lectureRemove)).toArray(LectureImpl[]::new);
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
            for (LectureImpl value : lectures) {
                int numb = value.getNumberOfLecture();
                if (numb == Integer.parseInt(item)) {
                    flag = false;
                    stringContains.append("\u001b[33;1m\"").append(item).append("\"\u001b[0m, ");
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
            lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLecture() == z)).toArray(LectureImpl[]::new);
        }
        sortLectureArrAfterRemove();
        return stringContains.toString();
    }

   //________________________________________________________________________________________________//

    private void sortLectureArr() {
        LectureImpl[] sortedArr = lectures;
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
        LectureImpl[] sortedArr = lectures;
        for (int i = 0; i < lectures.length; i++) {
            sortedArr[i].setNumberOfLecture(i + 1);
        }
        lectures = sortedArr;
    }



}
