package com.lms.spd;

import java.util.Arrays;

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
        this.literature = new Literature[0];
    }

    /**
     * lecture constructor with number and name
     */
    public Lectures(int numberOfLectures, String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = numberOfLectures;
        this.literature = new Literature[0];
    }

    /**
     * lecture constructor with name and literature
     */
    public Lectures(String nameOfLectures, String...lit) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = generateNumberOfLectures();

        Literature[] arrAddLit = new Literature[lit.length];
        for (int i = 0; i < arrAddLit.length; i++) {
            arrAddLit[i] = new Literature(lit[i]);
        }
        this.literature = arrAddLit;

    }

    /**
     * lecture constructor with name and literature
     */
    public Lectures(int numberOfLectures, String nameOfLectures, String... lit) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = numberOfLectures;
        Literature[] arrAddLit = new Literature[lit.length];
        for (int i = 0; i < arrAddLit.length; i++) {
            arrAddLit[i] = new Literature(lit[i]);
        }
        this.literature = arrAddLit;
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

    public Literature[] getLiterature() {
        return literature;
    }

    public void setLiterature(Literature[] literature) {
        this.literature = literature;
    }

    //show listLit
    public void printLitList() {
        if (literature.length > 0) {
            int i = 1;
            for (Literature x : literature
            ) {
                System.out.println(i + "." + x.toString());
                i++;
            }
        } else {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        }
    }

    public void addNewLit(String newBook) {
        Literature newLit = new Literature(newBook);
        Literature[] newArrayLiterature = Arrays.copyOf(literature, literature.length + 1);
        newArrayLiterature[newArrayLiterature.length - 1] = newLit;
        literature = newArrayLiterature;
    }

    public void deleteLit(int indexLit) {
        boolean flag = false;
        if (literature.length >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        System.out.println("successfully");

        if (literature.length == 1) {
            literature = new Literature[0];
        } else {
            Literature litTuDel = literature[indexLit-1];
            //Literature[] arrWichDellLit = new Literature[literature.length - 1]; // Array which will contain the result.
            literature = Arrays.stream(literature).filter(x -> !(x == litTuDel)).toArray(Literature[]::new);

        }
    }

    @Override
    public String toString() {
        return "Lecture №" + numberOfLectures +
                ". " + nameOfLectures;
    }


}
