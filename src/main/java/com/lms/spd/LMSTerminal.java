package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.AbstractLiterature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LMSTerminal {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();
    private LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
    private LMSConsolePrinter lmsConsolePrinter = new LMSConsolePrinter();


    public void startLMS() {
        lmsConsolePrinter.showStartMenu();
        try {
            switch (reader.readLine()) {
                case "1":
                    point1MainMenuShowLectures();
                case "2":
                    point2MainMenuAddingLecture2();
                    break;
                case "3":
                    point3MainMenuRemovalLecture();
                    break;
                case "4":
                    point4MainMenuChoiceOfLecture();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException | NullPointerException | ParseException e) {
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

    private void point2MainMenuAddingLecture2() throws IOException, ParseException {
        int numberOfLecture;
        String nameOfLecture;
        AbstractLiterature[] literatures;
        Date lectureDate;
        String lectorName;
        LectureType lectureType;
        System.out.println("Entering a new lecture");


        numberOfLecture = enterTheLectureNumber();
        nameOfLecture = enterTheLectureTitle();
        literatures = addLitOrNot();
        lectorName = enterLektorName();
        lectureDate = enterTheLectureDate();
        lectureType = setLectureType();

        lectureServiceImpl.addLecture(lectureType,numberOfLecture, nameOfLecture, literatures, lectorName, lectureDate);

        subMenuAddingLectureToList();
    }

    /*1*/
    private int enterTheLectureNumber() {
        System.out.println("Please enter number for new lecture if you don't want to press enter ");
        System.out.print("Number: ");
        int numberOfLecture = 21474836;
        try {
            numberOfLecture = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            //
        }
        return numberOfLecture;
    }

    /*2*/
    private String enterTheLectureTitle() throws IOException {
        String lectureName;
        while (true) {
            System.out.println("Enter the title of the lecture");
            lectureName = reader.readLine();
            if (!lectureName.isEmpty()) {
                break;
            }
            System.out.println("The lecture must have a title. Try again");
        }
        return lectureName;
    }

    /*3*/
    public AbstractLiterature[] addLitOrNot() throws IOException {
        AbstractLiterature[] result = new AbstractLiterature[0];
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        switch (reader.readLine()) {
            case "+":
                boolean flag = true;
                while (flag) {
                    result = addLitToArr(result);
                    System.out.println("Add more literature? if not enter minus");
                    if (reader.readLine().equals("-")) {
                        flag = false;
                    }
                }
                break;
            case "-":
                break;
            default:
                System.out.println("Something wrong");
                addLitOrNot();
        }
        return result;
    }

    public AbstractLiterature[] addLitToArr(AbstractLiterature[] arrAddLit) throws IOException {
        AbstractLiterature[] addLit = arrAddLit;
        System.out.println("what type of literature do you want to add ?");
        System.out.println("1.Book, 2.Journal article, 3.Internet article");
        //если выбрали то
        switch (Integer.parseInt(reader.readLine())) {
            case 1:
                AbstractLiterature newLit = literatureService.createBook();
                AbstractLiterature[] newArrayLiterature = Arrays.copyOf(addLit, addLit.length + 1);
                newArrayLiterature[newArrayLiterature.length - 1] = newLit;
                addLit = newArrayLiterature;
                break;
            case 2:
                AbstractLiterature newLit2 = literatureService.createJournal();
                AbstractLiterature[] newArrayLiterature2 = Arrays.copyOf(addLit, addLit.length + 1);
                newArrayLiterature2[newArrayLiterature2.length - 1] = newLit2;
                addLit = newArrayLiterature2;
                break;
            case 3:
                AbstractLiterature newLit3 = literatureService.createInternetArticles();
                AbstractLiterature[] newArrayLiterature3 = Arrays.copyOf(addLit, addLit.length + 1);
                newArrayLiterature3[newArrayLiterature3.length - 1] = newLit3;
                addLit = newArrayLiterature3;
                break;
            default:
                System.out.println("Try again");
                addLitToArr(arrAddLit);
                break;
        }
        return addLit;
    }

    /*4*/
    private String enterLektorName() throws IOException {
        System.out.println("Enter lecturer name");
        return reader.readLine();
    }

    /*5*/
    private Date enterTheLectureDate() throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Date d1 = new Date();
        String dateInString;
        dateInString = reader.readLine();
        if (dateInString.matches("[0-9]{2}[-][0-9]{2}[-][0-9]{4}")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            try {
                d1 = sdf.parse(dateInString);
            } catch (ParseException e) {
                //e.printStackTrace();//Always going to catch block
            }
        } else {
            System.out.println("Invalid date format");

        }
        return d1;
    }

    private LectureType setLectureType() throws IOException {
        System.out.println("Please, choose lecture type: ");
        System.out.println("1. " + LectureType.OPEN);
        System.out.println("2. " + LectureType.CLOSE);
        String numberStr = reader.readLine();
        int number = Integer.parseInt(numberStr);
        LectureType lectureType = LectureType.getValueByNumber(number);
        if(lectureType == null) {
            System.out.println("Unknown type: try again");
            setLectureType();
        }
        return lectureType;
    }

    private void subMenuAddingLectureToList() throws IOException, ParseException {
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        switch (reader.readLine().toUpperCase()) {
            case "+":
                point2MainMenuAddingLecture2();
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
        if (lectureServiceImpl.getLectures().length - 1 >= numbOfLectures - 1) {
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
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().length >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        lectureServiceImpl.getSelectedLecture().setLiteratures(literatureService.removeLiterature(indexLit, lectureServiceImpl.getSelectedLecture().getLiteratures()));
        System.out.println("\"Successfully\" Delete again ?");
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
        LectureIModel lecture = lectureServiceImpl.getSelectedLecture();
        StringBuilder lectureInfo = new StringBuilder("Lecture: №" + lecture.getNumberOfLecture() + " " + lecture.getNameOfLecture() + " \n");
        if (lecture.getLectorName() != null) {
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
