package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;

public class LitBookBuilder implements LitBuilder {
    LMSConsolePrinter print = new LMSConsolePrinter();

    @Override
    public Literature createLiterature() {
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        String title = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        String author = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_GENRE_NAME.printMassage();
        String genre = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_YEAR_OF_PUBLICATION.printMassage();
        int year = ConsoleInputValidator.readInt();
        return new BookModel(title, author, genre, year);
    }
}

