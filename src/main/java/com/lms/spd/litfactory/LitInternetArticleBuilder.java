package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.interfaces.Literature;

public class LitInternetArticleBuilder implements LitBuilder {
    LMSConsolePrinter print = new LMSConsolePrinter();

    @Override
    public Literature createLiterature() {
        LiteratureType typeLit = LiteratureType.INTERNET_ARTICLE;
        print.printMessagesAddLit(1);
        String title = ConsoleInputValidator.readString();
        print.printMessagesAddLit(2);
        String author = ConsoleInputValidator.readString();
        print.printMessagesAddLit(5);
        String urlAddress = ConsoleInputValidator.readString();
        return new InternetArticleModel(title, author, urlAddress);
    }

}
