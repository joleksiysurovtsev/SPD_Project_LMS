package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.interfaces.Literature;

public class LitInternetArticleBuilder implements LitBuilder {

    @Override
    public Literature createLiterature() {
        Literature internetArticle = new InternetArticleModel();
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        internetArticle.setTitle(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        internetArticle.setAuthor(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_URL_ADDRESS.printMassage();
        internetArticle.setUrlAddress(ConsoleInputValidator.readString());
        return internetArticle;
    }
}
