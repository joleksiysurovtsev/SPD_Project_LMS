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
        Literature lit;
        LiteratureType typeLit = getLiteratureType();
        print.printMessagesAddLit(1);
        String title = ConsoleInputValidator.readString();
        print.printMessagesAddLit(2);
        String author = ConsoleInputValidator.readString();
        switch (typeLit) {
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
                throw new IllegalStateException("Unexpected value: " + typeLit);
        }
        lit.setType(typeLit);
        return lit;
    }

    private LiteratureType getLiteratureType() {
        LiteratureType typeLit;
        System.out.println("Please, choose literature type: " + LiteratureType.toListString());
        while (true){
            int number = ConsoleInputValidator.readInt();
            typeLit = LiteratureType.stream().filter(d -> d.ordinal()==number).findFirst().orElse(null);
            if (typeLit!=null){
                break;
            }else {
                System.out.println("Unknown type: try again");
            }
        }
        return typeLit;
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
