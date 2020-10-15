package com.lms.spd;

public class Lectures {
    private String nameOfLectures;
    private int numberOfLectures;
    private String[] literature;

    /**
     * lecture constructor with name only
     */
    public Lectures(String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = generateNumberOfLectures();

    }

    private int generateNumberOfLectures() {
        int setNumber = 1;
        for (Lectures x : ListOfLectures.lectures) {
            if (setNumber == x.getNumberOfLectures()) {
                setNumber++;
            } else {
                break;
            }
        }
        return setNumber;
    }




    public Lectures(int numberOfLectures, String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = numberOfLectures;
    }

    public Lectures(String nameOfLectures, String[] literature) {
        this.nameOfLectures = nameOfLectures;
        this.literature = literature;
    }



    public int getNumberOfLectures() {
        return numberOfLectures;
    }

    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }


    @Override
    public String toString() {
        return "Lecture №" + numberOfLectures +
                ". " + nameOfLectures;
    }
}
