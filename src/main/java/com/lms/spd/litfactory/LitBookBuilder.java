package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;

public class LitBookBuilder implements LitBuilder {
    LMSConsolePrinter print = new LMSConsolePrinter();

    @Override
    public Literature createLiterature() {
        LiteratureType typeLit = LiteratureType.BOOK;
        print.printMessagesAddLit(1);
        String title = ConsoleInputValidator.readString();
        print.printMessagesAddLit(2);
        String author = ConsoleInputValidator.readString();
        print.printMessagesAddLit(6);
        String genre = ConsoleInputValidator.readString();
        print.printMessagesAddLit(7);
        int year = ConsoleInputValidator.readInt();
        return new BookModel(title, author, genre, year);
    }
}

