package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

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

public class LMSUtilsHelper {

    LMSConsolePrinter print = new LMSConsolePrinter();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public Literature createLit() throws IOException {
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

    public LectureType selectLectureType(List<LectureType> types) {
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

    public List<LectureType> arrayLectTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }

    public Calendar enterTheLectureDate() throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateInString = reader.readLine();
        try {
            sdf.setLenient(true);
            d1.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            System.out.println("The date is entered incorrectly, try again");
            enterTheLectureDate();}

        return d1;
    }





}
