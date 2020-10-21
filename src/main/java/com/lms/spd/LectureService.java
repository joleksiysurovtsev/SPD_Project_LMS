package com.lms.spd;

import java.util.Arrays;

public class LectureService {

    private Lecture[] lectures = {
            new Lecture(1, "BufferedReader."),
            new Lecture(2, "Writes text to."),
            new Lecture(3, "Core Java API"),
            new Lecture(4, "Core Java API4"),
    };

    public Lecture[] getLectures() {
        return lectures;
    }

    public void setLectures(Lecture[] lectures) {
        this.lectures = lectures;
    }

    private Lecture selectedLecture;

    public Lecture getSelectedLecture() {
        return selectedLecture;
    }

    public void setSelectedLecture(int selected) {
        Lecture select = new Lecture("");
        for (Lecture lecture : lectures) {
            if (lecture.getNumberOfLectures() == selected + 1) {
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
    public void addLecture(String name, Literature[] literature) {
        Lecture[] arrayAddedLectures = new Lecture[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Lecture addedLecture = new Lecture(arrayAddedLectures.length, name, literature);
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;
        lectures = arrayAddedLectures;
    }

    /**
     * Adds a new lecture by number and name, to an array
     */

    public void addLecturePlusNumber(int number, String name, Literature[] literature) {
        int numberOfLecture = number;
        Lecture[] arrayAddedLectures = new Lecture[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        if (numberOfLecture > arrayAddedLectures.length) {
            numberOfLecture = arrayAddedLectures.length;
        }
        Lecture addedLecture = new Lecture(numberOfLecture, name, literature);
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
    public boolean removeLectures(int lectureRemove) {
        boolean flag = false;
        for (Lecture value : lectures) {
            int numb = value.getNumberOfLectures();
            if (numb == lectureRemove) {
                lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == lectureRemove)).toArray(Lecture[]::new);
                sortLectureArrAfterRemove();
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String removeLectures(String lectureRemove) {
        String[] strings = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", "");
        }
        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : lectures) {
                int numb = value.getNumberOfLectures();
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
            lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == z)).toArray(Lecture[]::new);
        }
        sortLectureArrAfterRemove();
        return stringContains.toString();
    }

    //_______________________________________________________________________________________________//
    /**
     * the method adds new literature to the previously selected lecture
     */
    public void addNewLiterature(String newBook) {
        Literature[] litArr = selectedLecture.getLiterature();
        Literature newLit = new Literature(newBook);
        Literature[] newArrayLiterature = Arrays.copyOf(litArr, litArr.length + 1);
        newArrayLiterature[newArrayLiterature.length - 1] = newLit;
        selectedLecture.setLiterature(newArrayLiterature);
    }

    /**
     * method removes literature by number from a previously selected lecture
     */
    public void removeLiterature(int indexLit) {
        if (selectedLecture.getLiterature().length == 1) {
            selectedLecture.setLiterature(new Literature[0]);
        } else {
            Literature[] literature = selectedLecture.getLiterature();
            Literature litTuDel = literature[indexLit - 1];
            selectedLecture.setLiterature(Arrays.stream(literature).filter(x -> !(x == litTuDel)).toArray(Literature[]::new));
        }
    }
    //________________________________________________________________________________________________//

    private void sortLectureArr() {
        Lecture[] sortedArr = lectures;
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < lectures.length - 1; i++) {
                if (sortedArr[i].getNumberOfLectures() == sortedArr[i + 1].getNumberOfLectures()) {
                    sortedArr[i + 1].setNumberOfLectures((sortedArr[i + 1].getNumberOfLectures()) + 1);
                    flag = false;
                }
            }
        }
        lectures = sortedArr;
    }

    private void sortLectureArrAfterRemove() {
        Lecture[] sortedArr = lectures;
        for (int i = 0; i < lectures.length; i++) {
            sortedArr[i].setNumberOfLectures(i + 1);
        }
        lectures = sortedArr;
    }



}
