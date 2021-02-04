package com.lms.spd;

import com.lms.spd.error.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.utils.Util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LMSConsolePrinter {

    public static final int MIN = 60;
    public static final String TABLE_LINE = "+-------------------------------------------------------------------------------------------------------------------------+";
    public static final String TABLE_HEAD = "|\u1005|       Date       |  Lecture type      | Lecture number | Lecture ID  |                   Lecture title              |  Lecturer name    | Duration   |";
    public static final String HOURS = " hours ";
    public static final String MINUTES = " minutes";
    private String tabulator = "|%-1s| %-12s | %-19s|№: %-13d|№: %-10d|%-46.46s| %-18.18s|%-4.4s minutes|";
    private int count = 1;

    public void printAllLectureTable(List<Lecture> lectures) throws ListIsEmptyException {
        if (lectures.isEmpty()) {
            throw new ListIsEmptyException("I can not print the list of lectures it is empty");
        } else {
            printHeadTable();
            lectures.forEach(this::printLectureTable);
        }
        printLectureListStatistics(lectures);
    }

    public void printPreviewLectureList(List<Lecture> lectures) throws ListIsEmptyException {
        Util.GLOBAL_LOGGER.info("Lecture preview");
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.15s| %-24.24s|%-3s|";
        printAllLectureTable(lectures);
        tabulator = "|%-1s| %-12s| %-19s|№: %-13d|№: %-10d|%-46.46s| %-24.24s|%-3s|";
    }

    private void printHeadTable() {
        Util.GLOBAL_LOGGER.info(TABLE_LINE);
        Util.GLOBAL_LOGGER.info(TABLE_HEAD);
        Util.GLOBAL_LOGGER.info(TABLE_LINE);
    }

    /**
     * Method prints the table
     */
    public void printLectureTable(Lecture lecture) {
        String table = String.format(tabulator, "%n", "\u001b[31;1m\u1005\u001B[0m",
                new SimpleDateFormat("HH-mm-ss HH:mm").format(lecture.getLectureDate().getTime()),
                lecture.getType(), count++, lecture.getId(), lecture.getNameOfLecture(),
                lecture.getLectorName().trim(), lecture.getDurationOfTheLesson());

        Util.GLOBAL_LOGGER.info(table);
        Util.GLOBAL_LOGGER.info(TABLE_LINE);
    }

    /**
     * Method sorts the bibliography by type and date
     */
    public List<Literature> sortLitByDateAndType(List<Literature> litArr) {
        return litArr.stream()
                .distinct()
                .sorted(Comparator.comparing(Literature::getType)
                        .thenComparing(Literature::getDateResourceWasAdded))
                .collect(Collectors.toList());
    }

    public void printListLit(List<Literature> literature) {
        if (!literature.isEmpty()) {
            List<Literature> lit = sortLitByDateAndType(literature);
            int bound = literature.size();
            for (int id = 0; id < bound; id++) {
                String s = MessageFormat.format("{0} {1}", id + 1, lit.get(id).print());
                Util.GLOBAL_LOGGER.info(s);
            }
        } else {
            try {
                throw new ListIsEmptyException("Literature list is empty, please add literature first");
            } catch (ListIsEmptyException e) {
                Util.GLOBAL_LOGGER.info(e.getMessage());
            }
        }
    }

    /**
     * The method displays all information about the lesion
     */
    public void showAllLectureInfo(Lecture lecture) {
        String lectureInfo = "Lecture:ID" + lecture.getId() + " " + lecture.getNameOfLecture() + " \n" + "The lecture is lecturing by: "
                + lecture.getLectorName() + "\n" + "Lecture date:" + new SimpleDateFormat("HH-mm-ss HH:mm").format(lecture.getLectureDate().getTime())
                + "Type:" + lecture.getType();
        Util.GLOBAL_LOGGER.info(lectureInfo);
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
        Util.GLOBAL_LOGGER.info("\ntotal number of lectures " + statistics.getCount());
        Util.GLOBAL_LOGGER.info("average lecture time " + (int) (statistics.getAverage() / MIN) + HOURS + ((int) statistics.getAverage() % MIN) + MINUTES);
        Util.GLOBAL_LOGGER.info("minimal lecture time " + (statistics.getMin() / MIN) + HOURS + (statistics.getMin() % MIN) + MINUTES);
        Util.GLOBAL_LOGGER.info("maximum lecture time " + (statistics.getMax() / MIN) + HOURS + (statistics.getMax() % MIN) + MINUTES);
        Util.GLOBAL_LOGGER.info("all lecture time " + (statistics.getSum() / MIN) + HOURS + (statistics.getSum() % MIN) + MINUTES);
    }
}
