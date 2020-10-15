package com.lms.spd;

public class Lectures {
    private String nameOfLectures;
    private int numberOfLectures;
    private Literature[] literature;

    //lecture constructor____________________________________________________________________________________________//

    /**
     * lecture constructor with name only
     */
    public Lectures(String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = generateNumberOfLectures();
    }

    /**
     * lecture constructor with number and name
     */
    public Lectures(int numberOfLectures, String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;

        this.numberOfLectures = numberOfLectures;
    }

    /**
     * lecture constructor with name and literature
     */
    public Lectures(String nameOfLectures, Literature...lit) {
        this.nameOfLectures = nameOfLectures;
        this.literature = lit;
        this.numberOfLectures = generateNumberOfLectures();
    }

    /**
     * lecture constructor with name and literature
     */
    public Lectures(int numberOfLectures, String nameOfLectures, Literature...lit) {
        this.nameOfLectures = nameOfLectures;
        this.literature = lit;
        this.numberOfLectures = numberOfLectures;
    }



    //_______________________________________________________________________________________________________________//



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
