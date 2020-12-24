package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.JournalArticleModel;

public class LitJournalArticleBuilder implements LitBuilder {
    LMSConsolePrinter print = new LMSConsolePrinter();

    @Override
    public JournalArticleModel createLiterature() {
        LiteratureType typeLit = LiteratureType.JOURNAL_ARTICLE;
        print.printMessagesAddLit(1);
        String title = ConsoleInputValidator.readString();
        print.printMessagesAddLit(2);
        String author = ConsoleInputValidator.readString();
        print.printMessagesAddLit(3);
        String titleJournal = ConsoleInputValidator.readString();
        print.printMessagesAddLit(4);
        int issueOfTheJour = ConsoleInputValidator.readInt();
        return   new JournalArticleModel(title, author, titleJournal, issueOfTheJour);
    }
}
