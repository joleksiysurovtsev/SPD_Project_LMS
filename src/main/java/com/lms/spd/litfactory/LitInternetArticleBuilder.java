package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.interfaces.Literature;

public class LitInternetArticleBuilder implements LitBuilder {
    LMSConsolePrinter print = new LMSConsolePrinter();

    @Override
    public Literature createLiterature() {
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        String title = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        String author = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_URL_ADDRESS.printMassage();
        String urlAddress = ConsoleInputValidator.readString();
        return new InternetArticleModel(title, author, urlAddress);
    }

}
