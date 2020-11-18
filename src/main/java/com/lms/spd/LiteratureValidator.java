package com.lms.spd;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;

import java.util.*;

public class LiteratureValidator {
    LMSConsolePrinter print = new LMSConsolePrinter();
    LiteratureServiceImpl literatureServiceImpl = new LiteratureServiceImpl();

    public Literature createLit() {
        LiteratureType typeLit = LiteratureType.UNKNOWN;
        while (true) {
            System.out.println("Please, choose literature type: \n" + LiteratureType.toListString());
            int number = ConsoleInputValidator.readInt();
            if (Arrays.stream(LiteratureType.values()).anyMatch(e -> e.ordinal() == number)) {
                typeLit = LiteratureType.getValueByNumber(number);
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


    private Literature inputData(LiteratureType type) {
        Literature lit;
        print.printMessagesAddLit(1);
        String title = ConsoleInputValidator.readString();
        print.printMessagesAddLit(2);
        String author = ConsoleInputValidator.readString();
        switch (type) {
            case JOURNAL_ARTICLE:
                print.printMessagesAddLit(3);
                String titleJournal = ConsoleInputValidator.readString();
                print.printMessagesAddLit(4);
                int issueOfTheJour = ConsoleInputValidator.readInt();
                lit = new JournalArticleModel(title, author, titleJournal, issueOfTheJour);
                break;
            case INTERNET_ARTICLE:
                print.printMessagesAddLit(5);
                String urlAddress = ConsoleInputValidator.readString();
                lit = new InternetArticleModel(title, author, urlAddress);
                break;
            case BOOK:
                print.printMessagesAddLit(6);
                String genre = ConsoleInputValidator.readString();
                print.printMessagesAddLit(7);
                int year = ConsoleInputValidator.readInt();
                lit = new BookModel(title, author, genre, year);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        lit.setType(type);
        return lit;
    }
//____________________________________________________________________________________________________________________//


    List<Literature> addLitOrNot() {
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        List<Literature> newlit = new ArrayList<>();
        switch (ConsoleInputValidator.readString()) {
            case "+":
                do {
                    literatureServiceImpl.addLiterature(createLit(), newlit);
                    System.out.println("Add more literature? if not enter minus");
                } while (!ConsoleInputValidator.readString().equals("-"));
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


}
