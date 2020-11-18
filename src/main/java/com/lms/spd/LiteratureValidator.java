package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class LiteratureValidator {
    LMSConsolePrinter print = new LMSConsolePrinter();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    LiteratureServiceImpl literatureServiceImpl = new LiteratureServiceImpl();
    LectureValidator lectureValidator = new LectureValidator();

    public Literature createLit() throws IOException {
        LiteratureType typeLit = LiteratureType.UNKNOWN;
        while (true) {
            System.out.println("Please, choose literature type: \n" + LiteratureType.toListString());
            try {
                int number = Integer.parseInt(reader.readLine());
                if (Arrays.stream(LiteratureType.values()).anyMatch(e -> e.ordinal() == number)) {
                    typeLit = LiteratureType.getValueByNumber(number);
                }
            } catch (IOException | NumberFormatException e) {
                //
            }
            if (typeLit == LiteratureType.UNKNOWN) {
                System.out.println("Unknown type: try again");
            } else {
                break;
            }
        }
        assert typeLit != null;
        System.out.println("Type is : " + typeLit.toString());
        return inputData(typeLit);
    }


    public Literature inputData(LiteratureType type) throws IOException {
        Literature lit;
        print.printMessagesAddLit(1);
        String title = reader.readLine();
        print.printMessagesAddLit(2);
        String author = reader.readLine();
        switch (type) {
            case JOURNAL_ARTICLE:
                print.printMessagesAddLit(3);
                String titleJournal = reader.readLine();
                print.printMessagesAddLit(4);
                String issueOfTheJournal = reader.readLine();
                lit = createJournal(title, author, titleJournal, issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                print.printMessagesAddLit(5);
                String urlAddress = reader.readLine();
                lit = createInternetArticles(title, author, urlAddress);
                break;
            case BOOK:
                print.printMessagesAddLit(6);
                String genre = reader.readLine();
                print.printMessagesAddLit(7);
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
        String unknown = "Unknown";
        if (title.isEmpty()) {
            title = unknown;
        }
        if (author.isEmpty()) {
            author = unknown;
        }
        if (titleJournal.isEmpty()) {
            titleJournal = unknown;
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
//____________________________________________________________________________________________________________________//


    public List<LectureType> arrayLectTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }


    public List<Literature> addLitOrNot() throws IOException {
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        List<Literature> newlit = new ArrayList<>();
        switch (reader.readLine()) {
            case "+":
                do {
                    literatureServiceImpl.addLiterature(createLit(), newlit);
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
        return newlit;
    }

    public String[] stringToDeleteLecture(String lectureRemove, List<Lecture> list) {
        String[] numbDeletedLect = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, numbDeletedLect.length).forEach(i -> numbDeletedLect[i] = numbDeletedLect[i].replaceAll("[a-zA-Zа]*", ""));
        String[] numbToDisplay = Arrays.stream(numbDeletedLect).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : list) {
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

    void cashDate(LecturesCache cash) throws IOException {
        cash.setCurentDate(lectureValidator.enterTheLectureDate(this));
    }
}
