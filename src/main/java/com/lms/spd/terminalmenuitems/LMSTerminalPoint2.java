package com.lms.spd.terminalmenuitems;

import com.lms.spd.LMSTerminal;
import com.lms.spd.enums.LectureType;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

public class LMSTerminalPoint2 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
//    private LMSConsolePrinter lmsConsolePrinter = new LMSConsolePrinter();
    LMSTerminal terminal = new LMSTerminal();

    public LMSTerminalPoint2() throws IOException {
        point2MainMenuAddingLecture();
    }

    /**
     * point 2 main menu: adding new lectures
     */

    private void point2MainMenuAddingLecture() throws IOException {
        System.out.println("Please enter number for new lecture if you don't want to press enter \n\"Number: \"");
        int numberOfLecture = enterTheLectureNumber();

        System.out.println("Enter the title of the lecture");
        String nameOfLecture = enterTheLectureTitle();

        System.out.println("Enter lecturer name");
        String lectorName = enterLektorName();

        LectureType lectureType = selectLectureType();

        Calendar lectureDate = enterTheLectureDate();

        List<Literature> literatures = addLitOrNot();

        LMSTerminal.lectureServiceImpl.addLecture(new LectureIModel(lectureType, numberOfLecture, nameOfLecture, literatures, lectorName, lectureDate));

        System.out.println("Entering a new lecture");
        subMenuAddingLectureToList();
    }


    /**
     * If the lecture array is empty, then the first number in the lecture is assigned,
     * if the number is too large, then a number is assigned equal to the length of the array.
     */
    private int enterTheLectureNumber() {
        try {
            int x = Integer.parseInt(reader.readLine());
            return Math.min(x, LMSTerminal.lectureServiceImpl.getLectures().size() + 1);
        } catch (IOException | NumberFormatException e) {
            System.out.println("The wrong number format is entered, the last number of the lecture array will be assigned");
            return (LMSTerminal.lectureServiceImpl.getLectures().isEmpty()) ? 1 : LMSTerminal.lectureServiceImpl.getLectures().size() + 1;
        }
    }

    /**
     * Returns the title of the lecture after checking that it is not empty.
     */
    private String enterTheLectureTitle() throws IOException {
        String lectureName = reader.readLine();
        if (lectureName.isEmpty()) {
            System.out.println("The lecture must have a title. Try again");
            enterTheLectureTitle();
        }
        return lectureName;
    }

    /**
     * Method of adding the lecture date. If a date is entered,
     * the date is set to the wrong date on the day after two months from the current
     */
    private Calendar enterTheLectureDate() throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateInString = reader.readLine();
        try {
            sdf.setLenient(true);
            d1.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            d1 = Calendar.getInstance();
            System.out.println("The date is entered incorrectly, the date of the lecture is set two months from the current");
        }
        return d1;
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    private LectureType selectLectureType() {
        System.out.println("Please, choose lecture type: ");
        IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
            if (number > LectureType.values().length || number <= 0) {
                System.out.println("Unknown type: try again");
                selectLectureType();
            }
        } catch (IOException e) {
            System.out.println("Unknown type: try again");
            selectLectureType();
        }
        return LectureType.getValueByNumber(number);
    }

    private LectureType selectLectureType(List<LectureType> types) {
        int number;
        while (true) {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(0, types.size()).mapToObj(i -> (i + 1) + ". " + types.get(i) + " ").forEach(System.out::println);
            try {
                number = Integer.parseInt(reader.readLine());
                if (number > types.size() || number <= 0) {
                    System.out.println("Unknown type: try again");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return types.get(number - 1);
    }

    private List<LectureType> arrayLectTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }


    public List<Literature> addLitOrNot() throws IOException {
        List<Literature> newArrLit = new ArrayList<>();
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        switch (reader.readLine()) {
            case "+":
                do {
                    LMSTerminal.literatureServiceImpl.addLiterature(createLit(), newArrLit);
                    System.out.println("Add more literature? if not enter minus");
                } while (!reader.readLine().equals("-"));
                break;
            case "-":
                //или возвращаем пустой массив
                break;
            default:
                System.out.println("Something wrong");
                addLitOrNot();
        }
        return newArrLit;
    }


    private Literature createLit() throws IOException {
        LiteratureType typeLit = LiteratureType.UNKNOWN;
        boolean exists = true;
        while (exists) {
            System.out.println("Please, choose literature type: ");
            System.out.println("1.Book, 2.Journal article, 3.Internet article");
            try {
                int number = Integer.parseInt(reader.readLine());
                for (LiteratureType e : LiteratureType.values()) {
                    if (e.ordinal() == number) {
                        typeLit = LiteratureType.getValueByNumber(number);
                        exists = false;
                        break;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                //
            }
            if (typeLit != LiteratureType.UNKNOWN) {
                break;
            }
            System.out.println("Unknown type: try again");
        }
        assert typeLit != null;
        System.out.println("Type is : " + typeLit.toString());
        return inputData(typeLit);
    }


    public Literature inputData(LiteratureType type) throws IOException {
        Literature lit;
        LMSTerminal.lmsConsolePrinter.printMessagesAddLit(1);
        String title = reader.readLine();
        LMSTerminal.lmsConsolePrinter.printMessagesAddLit(2);
        String author = reader.readLine();
        switch (type) {
            case JOURNAL_ARTICLE:
                LMSTerminal.lmsConsolePrinter.printMessagesAddLit(3);
                String titleJournal = reader.readLine();
                LMSTerminal.lmsConsolePrinter.printMessagesAddLit(4);
                String issueOfTheJournal = reader.readLine();
                lit = createJournal(title, author, titleJournal, issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                LMSTerminal.lmsConsolePrinter.printMessagesAddLit(5);
                String urlAddress = reader.readLine();
                lit = createInternetArticles(title, author, urlAddress);
                break;
            case BOOK:
                LMSTerminal.lmsConsolePrinter.printMessagesAddLit(6);
                String genre = reader.readLine();
                LMSTerminal.lmsConsolePrinter.printMessagesAddLit(7);
                String year = reader.readLine();
                lit = createBook(title, author, genre, year);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        lit.setType(type);
        return lit;
    }

    public Literature createJournal(String title, String author, String titleJournal, String issueOfTheJournal) {
        if (title.isEmpty()) {
            title = "Unknown";
        }
        if (author.isEmpty()) {
            author = "Unknown";
        }
        if (titleJournal.isEmpty()) {
            titleJournal = "Unknown";
        }
        int issueOfTheJour;
        try {
            issueOfTheJour = Integer.parseInt(issueOfTheJournal);
        } catch (NumberFormatException | NullPointerException e) {
            issueOfTheJour = 0;
        }
        return new JournalArticleModel(title, author, titleJournal, issueOfTheJour);
    }

    public Literature createInternetArticles(String title, String author, String urlAddress) {
        if (title.isEmpty()) {
            title = "Unknown";
        }
        if (author.isEmpty()) {
            author = "Unknown";
        }
        if (urlAddress.isEmpty()) {
            urlAddress = "Unknown";
        }
        return new InternetArticleModel(title, author, urlAddress);
    }

    public Literature createBook(String title, String author, String genre, String year) {
        if (title.isEmpty()) {
            title = "Unknown";
        }
        if (author.isEmpty()) {
            author = "Unknown";
        }
        if (genre.isEmpty()) {
            genre = "Unknown";
        }
        int years;
        try {
            years = Integer.parseInt(year);
        } catch (NumberFormatException ignored) {
            years = 0;
        }
        return new BookModel(title, author, genre, years);
    }


    /**
     * Returns a string with the name of the lecturer, if no name is entered then the name is unknown
     * returns the title of the lecture after checking that it is not empty.
     */
    private String enterLektorName() throws IOException {
        String s = reader.readLine();
        return (s.isEmpty()) ? "Unknown" : s;
    }

    private void subMenuAddingLectureToList() throws IOException {
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        switch (reader.readLine().toUpperCase()) {
            case "+":
                point2MainMenuAddingLecture();
                break;
            case "-":
                terminal.startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"+\" \"-\" or EXIT");
                subMenuAddingLectureToList();
                break;
        }
    }






}
