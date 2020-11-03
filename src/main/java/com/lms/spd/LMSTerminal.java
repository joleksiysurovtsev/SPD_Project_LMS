package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.enums.LiteratureType;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LMSTerminal {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    private LMSConsolePrinter lmsConsolePrinter = new LMSConsolePrinter();


    public void startLMS() {
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
    public void point1MainMenuShowLectures() throws IOException {
        System.out.println("Display all lectures \u001b[36;1m\"+\"" + "\u001B[0m" + " or \u001b[31;1m\"-\" \u001B[0mspecifically some by numbers?" + " enter \u001B[32m\"small\"\u001B[0m to preview lectures");
        switch (reader.readLine().toLowerCase()) {
            case "+":
                lmsConsolePrinter.printLectureList(lectureServiceImpl.getLectures());
                System.out.println("The List shown what to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                lmsConsolePrinter.printLectureList(reader.readLine(), lectureServiceImpl.getLectures());
                System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            case "small":
                System.out.println("Lecture preview");
                lmsConsolePrinter.printPreviewLectureList(lectureServiceImpl.getLectures());
                System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            default:
                point1MainMenuShowLectures();
                break;
        }
        subMenuShowLectures();
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

        LectureType lectureType = setLectureType();

        Date lectureDate = enterTheLectureDate();

        ArrayList<Literature> literatures = addLitOrNot();


        lectureServiceImpl.addLecture(lectureType, numberOfLecture, nameOfLecture, literatures, lectorName, lectureDate);
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

    /*3*/
    public ArrayList<Literature> addLitOrNot() throws IOException {
        ArrayList<Literature> result = new ArrayList<>();
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        switch (reader.readLine()) {
            case "+":
                do {
                    addLitToArr(result);
                    System.out.println("Add more literature? if not enter minus");
                } while (!reader.readLine().equals("-"));
                break;
            case "-":
                break;
            default:
                System.out.println("Something wrong");
                addLitOrNot();
        }
        return result;
    }


    public List<Literature> addLitToArr(List<Literature> arrAddLit) throws IOException {
        int number = 0;
        boolean exists = true;
        LiteratureType typeLit = null;
        while (exists) {
            System.out.println("Please, choose literature type: ");
            System.out.println("1.Book, 2.Journal article, 3.Internet article");
            try {
                number = Integer.parseInt(reader.readLine());
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
            if (typeLit != null) {
                break;
            }
            System.out.println("Unknown type: try again");
        }
        System.out.println("Type is : " + typeLit.toString());
        arrAddLit.add(literatureServiceImpl.inputData(typeLit));
        return arrAddLit;
    }

    /**
     * Returns a string with the name of the lecturer, if no name is entered then the name is unknown
     * returns the title of the lecture after checking that it is not empty.
     */
    private String enterLektorName() throws IOException {
        String s = reader.readLine();
        return (s.isEmpty()) ? "Unknown" : s;
    }

    /**
     * Method of adding the lecture date. If a date is entered,
     * the date is set to the wrong date on the day after two months from the current
     */
    private Date enterTheLectureDate() throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Date d1 = new Date();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateInString = reader.readLine();
        try {
            sdf.setLenient(false);
            d1 = sdf.parse(dateInString);
        } catch (ParseException e) {
            LocalDateTime localDateTime = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMonths(2);
            d1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            System.out.println("The date is entered incorrectly, the date of the lecture is set two months from the current");
        }
        return d1;
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    private LectureType setLectureType() {
        System.out.println("Please, choose lecture type: ");
        System.out.println("1. " + LectureType.OPEN);
        System.out.println("2. " + LectureType.CLOSE);
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
            if (number > LectureType.values().length || number <= 0) {
                System.out.println("Unknown type: try again");
                setLectureType();
            }
        } catch (IOException e) {
            System.out.println("Unknown type: try again");
            setLectureType();
        }
        return LectureType.getValueByNumber(number);
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
            e.printStackTrace();
        }
        try {
            assert numbRemovalLecture != null;
            if (!lectureServiceImpl.removeLectures(Integer.parseInt(numbRemovalLecture))) {
                System.out.println("Lectures under this number do not exist, try again");
            } else {
                System.out.print("successfully");
            }
            subMenuRemovalLecture();
        } catch (NumberFormatException | IOException e) {
            System.out.println(lectureServiceImpl.removeLectures(numbRemovalLecture));
            subMenuRemovalLecture();
        }
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
            if (!checkNumberLecture(numbOfLecture)) {
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

    public boolean checkNumberLecture(int numbOfLectures) {
        boolean flag = false;
        if (lectureServiceImpl.getLectures().size()  >= numbOfLectures-1 ) {
            flag = true;
        }
        return flag;
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
        lmsConsolePrinter.printListLit(lectureServiceImpl.getSelectedLecture());
        System.out.println("what do we do with the bibliography");
        lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() throws IOException {
        System.out.println("Please enter the title of the new book");
        lectureServiceImpl.getSelectedLecture().setLiteratures(addLitToArr(lectureServiceImpl.getSelectedLecture().getLiteratures()));
        System.out.println("Book added what to do next");
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        String x = reader.readLine();

        while (true) {
            if (x.equalsIgnoreCase("+")) break;
            if (x.equalsIgnoreCase("-")) break;
            if (x.equalsIgnoreCase("exit")) break;
            System.out.println("You only need to enter a + or - or EXIT!");
            x = reader.readLine();
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
