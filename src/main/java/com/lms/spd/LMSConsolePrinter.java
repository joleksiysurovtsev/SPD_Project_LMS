package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LMSConsolePrinter {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    String tabulator = "|%-1s| %-12s | %-19s|№: %-13d|№: %-10d|%-46.46s| %-18.18s|%-4.4s minutes|";
    private static int count = 1;

    public void printAllLectureTable(List<Lecture> lectures) throws ListIsEmptyException {
        if (lectures.isEmpty()) {
            throw new ListIsEmptyException("I can not print the list of lectures it is empty");
        } else {
            printTopOfTable();
            lectures.forEach(this::printLectureTable);
            count = 1;
        }
        printLectureListStatistics(lectures);
    }

    public void printPreviewLectureList(List<Lecture> lectures) throws ListIsEmptyException {
        System.out.println("Lecture preview");
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.15s| %-24.24s|%-3s|";
        printAllLectureTable(lectures);
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.46s| %-24.24s|%-3s|";
    }

    public void printLectureListByType(LectureType type, List<Lecture> lectures) throws ListIsEmptyException {
        if (lectures.isEmpty()) {
            throw new ListIsEmptyException("I can not print the list of lectures it is empty");
        } else {
            printTopOfTable();
            lectures.stream().filter(lecture -> lecture.getType() == type).forEach(this::printLectureTable);
            count = 1;
        }
    }

    public void printLectureListByNumber(String s, List<Lecture> lectures) throws ListIsEmptyException {
        if (lectures.isEmpty()) {
            throw new ListIsEmptyException("I can not print the list of lectures it is empty");
        } else {
            printTopOfTable();
            int[] numbToDisplay = Util.getStringsNumberLecture(s);

            Arrays.stream(numbToDisplay).forEach(value -> lectures.stream()
                    .filter(x -> value == x.getId())
                    .forEach(this::printLectureTable));
        }
    }

    private void printTopOfTable() {
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|\u1005|       Date       |  Lecture type      | Lecture number | Lecture ID  |                   Lecture title              |  Lecturer name    | Duration   |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    /**
     * Method prints the table
     */
    public void printLectureTable(Lecture lecture) {
        if (lecture.getLectureDate().before(Calendar.getInstance())) {
            System.out.println(String.format(tabulator, "\u001b[31;1m\u1005\u001B[0m", sdf.format(lecture.getLectureDate().getTime()), lecture.getType(), count++, lecture.getId(), lecture.getNameOfLecture(), lecture.getLectorName().trim(), lecture.getDurationOfTheLesson()));
        } else {
            System.out.println(String.format(tabulator, "\u001b[32;1m\u1005\u001B[0m", sdf.format(lecture.getLectureDate().getTime()), lecture.getType(), count++, lecture.getId(), lecture.getNameOfLecture(), lecture.getLectorName().trim(), lecture.getDurationOfTheLesson()));
        }
    }

    /**
     * Method sorts the bibliography by type and date
     */
    public List<Literature> sortLitByDateAndType(List<Literature> litArr) {
        return litArr.stream().distinct().sorted(Comparator.comparing(Literature::getType).thenComparing(Literature::getDateResourceWasAdded)).collect(Collectors.toList());
    }

    /**
     * method displays the main menu
     */
    public void showStartMenu() {
        System.out.println("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" +
                "\"" + ": learning management system" + "\u001B[0m\n" +
                "\"Please make your choice from the offered options\"\n"
                + "1. Display lectures (number and title)\n" + "2. Add a new lecture\n"
                + "3. Delete a lecture by its ID\n" + "4. Choose a lecture by its ID\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    /**
     * point 4 main menu: method deleting the lecture list
     */
    public void showFourthMenu() {
        System.out.println("\"1. --> choose another lecture\n" + "\"2. --> view the list of literature\n" +
                "\"3. --> add new literature\n" + "\"4. --> remove literature\n" +
                "\"5. --> view all lecture information\n" + "\"6. --> exit to the main menu");
    }

    /**
     * The method displays messages when creating literature
     */
    public void printMessagesAddLit(int message) {
        Map<Integer, String> massageMap = Map.of(1, "Please enter a title:",
                2, "Please enter a author name",
                3, "Please enter a titleJournal name or press Enter",
                4, "Please enter a issue of the journal where the article was published",
                5, "Please enter a url address or press Enter",
                6, "Please enter a genre name or press Enter",
                7, "Please enter a year of publication of the book");
        if (massageMap.containsKey(message)) {
            System.out.println(massageMap.get(message));
        }
    }

    /**
     * The method displays a List<Lecture>,
     * it also checks the List<Lecture> is empty or not,
     * if List<Lecture> is empty, then throws an error and processes it
     */
    public void printListLit(Lecture lecture) {
        List<Literature> litArr = lecture.getLiteratures();
        if (litArr.isEmpty()) {
            try {
                throw new ListIsEmptyException("Literature list is empty, please add literature first");
            } catch (ListIsEmptyException e) {
                System.err.println(e.getMessage());
            }
        } else {
            litArr = sortLitByDateAndType(litArr);
            int i = 1;
            for (Literature x : litArr) {
                System.out.println(i + "" + x.print());
                i++;
            }
        }
    }

    /**
     * Method of displaying the first item of the terminal main menu
     */
    public void printMenuPoint1() {
        System.out.println("\u001b[36;1m\"+\"\u001B[0m Display all lectures\n" + "\u001b[31;1m\"-\" \u001B[0mSpecifically some by ID\n"
                + "\u001B[32m\"SMALL\"\u001B[0m To preview lectures\n" + "\u001B[35m\"TYPE\"\u001B[0m Display lectures of a certain type \n"
                + "\u001B[36m\"DATE\"\u001B[0m Display lectures by curend date\n"
                + "\u001B[34m\"TYPE AND DATE\"\u001B[0m Display lectures by curend date\n"
                + "\u001B[31m\"EXIT\"\u001B[0m To go to the main menu");
    }

    public void printErrMassage(int message) {
        Map<Integer, String> massageMap = Map.of(1, "There is no such item in the menu, let's try again",
                2, "There is no such item in the menu, let's try again");
        if (massageMap.containsKey(message)) {
            System.err.println(massageMap.get(message));
        }
    }

    /**
     * The method displays all information about the lesion
     */
    void showAllLectureInfo(Lecture lecture) {
        String lectureInfo = "Lecture: ID" + lecture.getId() + " " + lecture.getNameOfLecture() + " \n" + "The lecture is lecturing by: " + lecture.getLectorName() + "\n" +
                "Lecture date: " + sdf.format(lecture.getLectureDate().getTime()) +
                " Lecture Type: " + lecture.getType();
        System.out.println(lectureInfo);
        printListLit(lecture);
    }

    /**
     * <b>The method prints a List of  {@code <Lectures>}  by {@code LectureType} </b>
     */
    public void printLectureListByType(LectureType selectLectureType, Map<LectureType, List<Lecture>> mapSortedByType) throws ListIsEmptyException {
        List<Lecture> lectureList = mapSortedByType.get(selectLectureType);
        printAllLectureTable(lectureList);
    }


    /**
     * The method prints a List of  {@link com.lms.spd.models.interfaces.Lecture Lectures}
     * by {@link com.lms.spd.enums.LectureType LectureType}  & {@link java.util.GregorianCalendar Date} </b>
     */
    public void printLectureListByTypeAndDate(LectureType selectLectureType, Map<LectureType, List<Lecture>> mapSortedByType, Calendar currentdate) throws ListIsEmptyException {
        Map<Boolean, List<Lecture>> booleanListMap = Util.getCollectByDate(selectLectureType, mapSortedByType, currentdate);
        if (booleanListMap.containsKey(false)) {
            System.out.println("on this date such lectures have been completed");
            printAllLectureTable(booleanListMap.get(false));
        }
        if (booleanListMap.containsKey(true)) {
            System.out.println("such lectures to be held");
            printAllLectureTable(booleanListMap.get(true));
        }
    }

    /**
     * <b>Method prints statistics about lecture time
     * <pre>{@code "total number of lecture"
     * "average lecture time"
     * "minimal lecture time"
     * "maximum lecture time"
     * "maximum lecture time"
     * </pre>
     */
    public void printLectureListStatistics(List<Lecture> lectures) {
        IntSummaryStatistics statistics = Arrays.stream((lectures.stream().mapToInt(Lecture::getDurationOfTheLesson).toArray())).summaryStatistics();
        System.out.println("\ntotal number of lectures " + statistics.getCount());
        System.out.println("average lecture time " + (int) (statistics.getAverage() / 60) + " hours " + ((int) statistics.getAverage() % 60) + " minutes");
        System.out.println("minimal lecture time " + (statistics.getMin() / 60) + " hours " + (statistics.getMin() % 60) + " minutes");
        System.out.println("maximum lecture time " + (statistics.getMax() / 60) + " hours " + (statistics.getMax() % 60) + " minutes");
        System.out.println("all lecture time " + (statistics.getSum() / 60) + " hours " + (statistics.getSum() % 60) + " minutes");
    }
}
