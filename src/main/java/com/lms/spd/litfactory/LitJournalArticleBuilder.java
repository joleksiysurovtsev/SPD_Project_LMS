package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;

public class LitJournalArticleBuilder implements LitBuilder {

    @Override
    public Literature createLiterature() {
        Literature journalArticle = new JournalArticleModel();
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        journalArticle.setTitle(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        journalArticle.setAuthor(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_TITLE_JOURNAL.printMassage();
        journalArticle.setTitleOfArticle(ConsoleInputValidator.readString());
        ConsoleMassage.MESSAGE_ENTER_ISSUE_OF_THE_JOURNAL.printMassage();
        journalArticle.setIssueOfTheJournal(ConsoleInputValidator.readInt());
        return journalArticle;
    }
}

