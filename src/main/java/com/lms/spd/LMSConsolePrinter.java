package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class LMSConsolePrinter {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String tabulator = "|%-1s| %-12s| %-19s|№: %-13d|%-50.50s| %-24.24s|";

    public void printAllLectureTable(List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
        } else {
            printTopOfTable();
            lectures.forEach(this::printLectureTable);
        }
    }

    public void printPreviewLectureList(List<Lecture> lectures) {
        System.out.println("Lecture preview");
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|%-50.15s| %-24.24s|";
        printAllLectureTable(lectures);
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|%-50.50s| %-24.24s|";
    }

    public void printLectureListByType(LectureType type, List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
        } else {
            printTopOfTable();
            lectures.stream().filter(lect -> lect.getType() == type).forEach(this::printLectureTable);
        }
    }

    public void printLectureListByNumber(String s, List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
        } else {
            printTopOfTable();
            int[] numbToDisplay = getStringsNumberLect(s);
            Arrays.stream(numbToDisplay).forEach(value -> lectures.stream()
                    .filter(x -> value == x.getNumberOfLecture())
                    .forEach(this::printLectureTable));
        }
    }

    private void printTopOfTable() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|\u1005|    Date     |  Lecture type      | Lecture number |                          Lecture title           |      Lecturer name      |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
    }

    //Печатает таблицу
    private void printLectureTable(Lecture lecture) {
        if (lecture.getLectureDate().before(Calendar.getInstance())) {
            System.out.println(String.format(tabulator, "\u001b[31;1m\u1005\u001B[0m", sdf.format(lecture.getLectureDate().getTime()), lecture.getType(), lecture.getNumberOfLecture(), lecture.getNameOfLecture(), lecture.getLectorName().trim()));
        } else {
            System.out.println(String.format(tabulator, "\u001b[32;1m\u1005\u001B[0m", sdf.format(lecture.getLectureDate().getTime()), lecture.getType(), lecture.getNumberOfLecture(), lecture.getNameOfLecture(), lecture.getLectorName().trim()));
        }
    }

    /**
     * returns numbers from strings
     */
    private int[] getStringsNumberLect(String s) {
        String[] strings = s.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, strings.length).forEach(i -> strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", ""));
        return Arrays.stream(strings).filter(x -> !(x.isEmpty())).mapToInt(Integer::parseInt).toArray();
    }

    public void sortLitByDateAndType(List<Literature> litArr) {
        litArr.sort(Comparator.comparing(Literature::getType));
        boolean flag = false;
        while (flag) {
            flag = true;
            for (int i = 0, litArrSize = litArr.size(); i < litArrSize; i++) {
                if (litArr.get(i).getDateResourceWasAdded().after(litArr.get(i + 1))) {
                    //меняем их местами
                    Literature temps = litArr.get(i);
                    litArr.set(i, litArr.get(i + 1));
                    litArr.set(i + 1, temps);
                    flag = false;
                }
            }
        }
    }

    public void showStartMenu() {
        System.out.println("\u001B[34m" + "Main menu " +
                "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" +
                "\"" + ": learning management system" + "\u001B[0m\n" +
                "\"Please make your choice from the offered options\n"
                + "1. Display lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    /**
     * point 4 main menu: method deleting the lecture list
     */

    public void showFourthMenu() {
        System.out.println("1. --> choose another lecture\n" +
                "\"2. --> view the list of literature\n" +
                "\"3. --> add new literature\n" +
                "\"4. --> remove literature\n" +
                "\"5. --> view all lecture information\n" +
                "\"6. --> exit to the main menu");
    }

    public void printMessagesAddLit(int message) {
        if (message == 1) {
            System.out.println("Enter a title");
        }
        if (message == 2) {
            System.out.println("Please enter a author name");
        }
        if (message == 3) {
            System.out.println("Please enter a titleJournal name or press Enter");
        }
        if (message == 4) {
            System.out.println("Please enter a issue of the journal where the article was published");
        }
        if (message == 5) {
            System.out.println("Please enter a url address or press Enter");
        }
        if (message == 6) {
            System.out.println("Please enter a genre name or press Enter");
        }
        if (message == 7) {
            System.out.println("Please enter a year of publication of the book");
        }
    }

    public void printListLit(Lecture lecture) {
        //создали из лекции лист литературы
        List<Literature> litArr = lecture.getLiteratures();
        //если список пустой
        if (litArr.isEmpty()) {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        } else {
            sortLitByDateAndType(litArr);
            //печатаем на экран литературу
            int i = 1;
            for (Literature x : litArr) {
                System.out.println(i + "" + x.print());
                i++;
            }
        }
    }
}
