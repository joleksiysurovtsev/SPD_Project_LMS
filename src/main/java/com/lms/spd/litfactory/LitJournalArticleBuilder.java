package com.lms.spd.litfactory;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.JournalArticleModel;

import java.util.Calendar;

public class LitJournalArticleBuilder implements LitBuilder {

    @Override
    public JournalArticleModel createLiterature() {
        ConsoleMassage.MESSAGE_ENTER_TITLE.printMassage();
        String title = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_AUTHOR.printMassage();
        String author = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_TITLE_JOURNAL.printMassage();
        String titleJournal = ConsoleInputValidator.readString();
        ConsoleMassage.MESSAGE_ENTER_ISSUE_OF_THE_JOURNAL.printMassage();
        int issueOfTheJour = ConsoleInputValidator.readInt();
        return new JournalArticleModel(title, author, LiteratureType.JOURNAL_ARTICLE, Calendar.getInstance(), titleJournal, issueOfTheJour);
    }
}

