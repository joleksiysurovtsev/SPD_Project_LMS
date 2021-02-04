package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;

public class LitBookBuilder implements LitBuilder {
    @Override
    public Literature createLiterature() {
        Literature bookModel = new BookModel();
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        bookModel.setTitle(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        bookModel.setAuthor(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_GENRE_NAME.printMassage();
        bookModel.setGenre(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_YEAR_OF_PUBLICATION.printMassage();
        bookModel.setPublishedInYear(ConsoleInputValidator.readInt());
        return bookModel;
    }
}

