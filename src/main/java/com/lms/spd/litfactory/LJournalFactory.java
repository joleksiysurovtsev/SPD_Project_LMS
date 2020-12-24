package com.lms.spd.litfactory;

public class LJournalFactory implements LitFactory {
    @Override
    public LitBuilder createLitBuilder() {
        return new LitJournalArticleBuilder();
    }
}
