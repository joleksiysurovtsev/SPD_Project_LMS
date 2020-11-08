package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class LMSTerminal {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    private LMSConsolePrinter lmsConsolePrinter = new LMSConsolePrinter();
    public static LecturesCash cash = new LecturesCash();


    public void startLMS() {
        //    cash.printMap();
        lmsConsolePrinter.showStartMenu();
        try {
            switch (reader.readLine()) {
                case "1":
                    point1MainMenuShowLectures();
                    break;
                case "2":
                    point2MainMenuAddingLecture();
                    break;
                case "3":
                    point3MainMenuRemovalLecture();
                    break;
                case "4":
                    point4MainMenuChoiceOfLecture();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws IOException {
        System.out.println("\u001b[36;1m\"+\"\u001B[0m Display all lectures\n"
                + "\u001b[31;1m\"-\" \u001B[0mSpecifically some by numbers\n"
                + "\u001B[32m\"SMALL\"\u001B[0m To preview lectures\n"
                + "\u001B[35m\"TYPE\"\u001B[0m Display lectures of a certain type \n"
                + "\u001B[36m\"DATE\"\u001B[0m Display lectures by curend date ");
        switch (reader.readLine().toLowerCase()) {
            case "+":
                lmsConsolePrinter.printLectureList(lectureServiceImpl.getLectures());
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                lmsConsolePrinter.printLectureList(reader.readLine(), lectureServiceImpl.getLectures());
                break;
            case "small":
                lmsConsolePrinter.printPreviewLectureList(lectureServiceImpl.getLectures());
                break;
            case "type":
                lmsConsolePrinter.printLectureListByType(selectLectureType(arrayLectTypesInvolved(lectureServiceImpl.getLectures())), lectureServiceImpl.getLectures());
                break;
            case "date":
                //*тут дожен быть метод смены даты*//
                changeDate();

                lmsConsolePrinter.printLectureList(cash.returnList());
                break;
            default:
                point1MainMenuShowLectures();
                break;
        }
        System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program ");
        subMenuShowLectures();
    }

    private void changeDate() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.print("Displayed lectures for :" + sdf.format(cash.getCurentDate().getTime()) + " if you want to change the date enter otherwise press enter ");
        if (!reader.readLine().isEmpty()) {
            cash.setCurentDate(enterTheLectureDate());
        }
        System.out.println("Date " + sdf.format(cash.getCurentDate().getTime()));
    }


    private void subMenuShowLectures() throws IOException {
        switch (reader.readLine().toUpperCase()) {
            case "0":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"0\" or EXIT");
                subMenuShowLectures();
                break;
        }
    }

    //______________________________________________________________________________________________________________//

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

        lectureServiceImpl.addLecture(new LectureIModel(lectureType, numberOfLecture, nameOfLecture, literatures, lectorName, lectureDate));

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
            return Math.min(x, lectureServiceImpl.getLectures().size() + 1);
        } catch (IOException | NumberFormatException e) {
            System.out.println("The wrong number format is entered, the last number of the lecture array will be assigned");
            return (lectureServiceImpl.getLectures().isEmpty()) ? 1 : lectureServiceImpl.getLectures().size() + 1;
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
                    literatureServiceImpl.addLiterature(createLit(), newArrLit);
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
        lmsConsolePrinter.printMessagesAddLit(1);
        String title = reader.readLine();
        lmsConsolePrinter.printMessagesAddLit(2);
        String author = reader.readLine();
        switch (type) {
            case JOURNAL_ARTICLE:
                lmsConsolePrinter.printMessagesAddLit(3);
                String titleJournal = reader.readLine();
                lmsConsolePrinter.printMessagesAddLit(4);
                String issueOfTheJournal = reader.readLine();
                lit = createJournal(title, author, titleJournal, issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                lmsConsolePrinter.printMessagesAddLit(5);
                String urlAddress = reader.readLine();
                lit = createInternetArticles(title, author, urlAddress);
                break;
            case BOOK:
                lmsConsolePrinter.printMessagesAddLit(6);
                String genre = reader.readLine();
                lmsConsolePrinter.printMessagesAddLit(7);
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
                startLMS();
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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    /**
     * point 3 main menu: method deleting the lecture list
     */

    private void point3MainMenuRemovalLecture() throws IOException {
        System.out.println("Please enter the number of the lecture if you want to delete one or more comma separated ");
        String numbRemovalLecture = null;
        try {
            numbRemovalLecture = reader.readLine();
        } catch (IOException e) {
            point3MainMenuRemovalLecture();
        }
        assert numbRemovalLecture != null;
        String[] lectureRemove = stringToDeleteLecture(numbRemovalLecture);
        lectureServiceImpl.removeLectures(lectureRemove);
        subMenuRemovalLecture();

    }


    public String[] stringToDeleteLecture(String lectureRemove) {
        String[] numbDeletedLect = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, numbDeletedLect.length).forEach(i -> numbDeletedLect[i] = numbDeletedLect[i].replaceAll("[a-zA-Zа]*", ""));
        String[] numbToDisplay = Arrays.stream(numbDeletedLect).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : lectureServiceImpl.getLectures()) {
                int numb = value.getNumberOfLecture();
                if (numb == Integer.parseInt(item)) {
                    flag = false;
                    stringContains.append(" ").append(item).append(" ");
                    break;
                }
            }
        }
        stringContains.append(!flag ? "successfully removed the rest are missing." : "are missing.");
        System.out.println(stringContains.toString());
        return numbToDisplay;
    }

    private void subMenuRemovalLecture() throws IOException {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");

        switch (reader.readLine().toUpperCase()) {
            case "+":
                point3MainMenuRemovalLecture();
                break;
            case "-":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                subMenuRemovalLecture();
                break;
        }
    }

    //_______________________________________________________________________________________________________________//

    private void point4MainMenuChoiceOfLecture() throws IOException {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");
        int numbOfLecture = 0;
        boolean flag = true;
        while (flag) {
            //проверили ввели ли номер
            try {
                numbOfLecture = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You must type a lecture number!");
            }
        }
        if (numbOfLecture == 0) {
            startLMS();
        } else {
            if (!(lectureServiceImpl.getLectures().size() >= numbOfLecture - 1)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                point4MainMenuChoiceOfLecture();
            } else {
                lectureServiceImpl.setSelectedLecture(numbOfLecture - 1);
                System.out.println("Selected lecture " + "\u001B[35m" + "\"" + lectureServiceImpl.getSelectedLecture().toString() + "\"" + "\u001B[0m");
                System.out.println("What are the next actions?");
                System.out.println("__________________________");
            }
        }
        lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }

    private void subMenu2Point4() throws IOException {
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                choice = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException | IOException e) {
                System.out.println("You only need to enter a number!");

            }
        }
        switch (choice) {
            case 1: // 1. --> choose another lecture
                point4MainMenuChoiceOfLecture();
                break;
            case 2://  2. --> view the list of literature
                point4_2ViewListOfLit();
                break;
            case 3://  3. --> add new literature
                point4_3AddLit();
                subMenu2Point4();
                break;
            case 4://  4. --> remove literature
                point4_4DeleteLit();
                break;
            case 5://  4. --> show lecture info
                point4_5showLectureInfo();
                break;
            case 6:
                startLMS();
                break;
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                subMenu2Point4();
                break;
        }
    }


    private void point4_2ViewListOfLit() throws IOException {
        lectureServiceImpl.getSelectedLecture().printListLit(lmsConsolePrinter);
        System.out.println("what do we do with the bibliography");
        lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() throws IOException {
        Literature newLit = createLit();
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().contains(newLit)){
            System.out.println("this literature is already there");
        }else{
            literatureServiceImpl.addLiterature(newLit, lectureServiceImpl.getSelectedLecture().getLiteratures());
            System.out.println("Book added what to do next");
        }
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        String x;
        while (true) {
            x = reader.readLine();
            if (x.equalsIgnoreCase("+") || x.equalsIgnoreCase("-") || x.equalsIgnoreCase("exit")) break;
            System.out.println("You only need to enter a + or - or EXIT!");
        }
        switch (x.toUpperCase()) {
            case "+":
                point4_3AddLit();
                break;
            case "-":
                point4MainMenuChoiceOfLecture();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void point4_4DeleteLit() throws IOException {
        System.out.println("Please enter the number of the book you want to delete");
        int indexLit = 0;
        try {
            indexLit = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            point4_4DeleteLit();
        }
        boolean flag = false;
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().size() >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        lectureServiceImpl.getSelectedLecture().setLiteratures(literatureServiceImpl.removeLiterature(indexLit, lectureServiceImpl.getSelectedLecture().getLiteratures()));
        System.out.println("\"Successfully\" Delete again ?");
        System.out.println("\"+\" YES or \"-\" NO");
        subMenuPoint4_4DeleteLit();
    }

    private void subMenuPoint4_4DeleteLit() throws IOException {
        System.out.println("\"+\" YES or \"-\" NO");
        switch (reader.readLine()) {
            case "+":
                point4_4DeleteLit();
                break;
            case "-":
                point4MainMenuChoiceOfLecture();
                break;
            default:
                System.out.println("please choice from the offered");
                break;
        }
    }

    private void point4_5showLectureInfo() throws IOException {
        Lecture lecture = lectureServiceImpl.getSelectedLecture();
        StringBuilder lectureInfo = new StringBuilder("Lecture: №" + lecture.getNumberOfLecture() + " " + lecture.getNameOfLecture() + " \n");
        if (!lecture.getLectorName().isEmpty() || lecture.getLectorName() != null) {
            lectureInfo.append("The lecture is lecturing by: ").append(lecture.getLectorName()).append("\n");
        }
        if (lecture.getLectureDate() != null) {
            lectureInfo.append("Lecture date: ").append(lecture.getLectureDate());
        }
        if (lecture.getType() != null) {
            lectureInfo.append(" Lecture Type: ").append(lecture.getType());
        }
        System.out.println(lectureInfo);
        lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }
}
