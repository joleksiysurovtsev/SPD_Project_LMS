package com.lms.spd;

import com.lms.spd.error.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LMSConsolePrinter {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    String tabulator = "|%-1s| %-12s | %-19s|№: %-13d|№: %-10d|%-46.46s| %-18.18s|%-4.4s minutes|";
    private static int count = 1;

    public void printAllLectureTable(List<Lecture> lectures) throws ListIsEmptyException {
        if (lectures.isEmpty()) {
            throw new ListIsEmptyException("I can not print the list of lectures it is empty");
        } else {
            System.out.println(lectures);
            printTopOfTable();
            lectures.forEach(this::printLectureTable);
        }
        printLectureListStatistics(lectures);
    }

    public void printPreviewLectureList(List<Lecture> lectures) throws ListIsEmptyException {
        System.out.println("Lecture preview");
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.15s| %-24.24s|%-3s|";
        printAllLectureTable(lectures);
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.46s| %-24.24s|%-3s|";
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
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------+");
        } else {
            System.out.println(String.format(tabulator, "\u001b[32;1m\u1005\u001B[0m", sdf.format(lecture.getLectureDate().getTime()), lecture.getType(), count++, lecture.getId(), lecture.getNameOfLecture(), lecture.getLectorName().trim(), lecture.getDurationOfTheLesson()));
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    /**
     * Method sorts the bibliography by type and date
     */
    public List<Literature> sortLitByDateAndType(List<Literature> litArr) {
        return litArr.stream().distinct().sorted(Comparator.comparing(Literature::getType).thenComparing(Literature::getDateResourceWasAdded)).collect(Collectors.toList());
    }


    /**
     * The method displays a List<Literature>,
     * it also checks the List<Literature> is empty or not,
     * if List<Literature> is empty, then throws an error and processes it
     */
    public void printListLit(List<Literature> literature) {
        if (!literature.isEmpty()) {
            List<Literature> lit = sortLitByDateAndType(literature);
            IntStream.range(0, literature.size())
                    .mapToObj(id -> (id + 1) + " " + lit.get(id).print())
                    .forEach(System.out::println);
        } else {
            try {
                throw new ListIsEmptyException("Literature list is empty, please add literature first");
            } catch (ListIsEmptyException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * The method displays all information about the lesion
     */
    public void showAllLectureInfo(Lecture lecture) {
        String lectureInfo = "Lecture: ID" + lecture.getId() + " " + lecture.getNameOfLecture() + " \n" + "The lecture is lecturing by: " + lecture.getLectorName() + "\n" +
                "Lecture date: " + sdf.format(lecture.getLectureDate().getTime()) +
                " Lecture Type: " + lecture.getType();
        System.out.println(lectureInfo);
        printListLit(lecture.getLiteratures());
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
