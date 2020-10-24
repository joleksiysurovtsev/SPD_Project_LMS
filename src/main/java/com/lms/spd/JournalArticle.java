package com.lms.spd;

import java.util.Objects;

public class JournalArticle extends Literature {
    String titleOfArticle;
    int issueOfTheJournal;

    public JournalArticle(String titleOfArticle, String author, String titleJournal, int issueOfTheJournal ) {
        setAuthor(author);
        setTitle(titleJournal);
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
    }

    public JournalArticle(String titleOfArticle, String author, String titleJournal ) {
        setAuthor(author);
        setTitle(titleJournal);
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = 0;
    }

    public JournalArticle(String titleOfArticle, String author) {
        setAuthor(author);
        setTitle("N/A");
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = 0;
    }


    public String getTitleOfArticle() {
        return titleOfArticle;
    }

    public void setTitleOfArticle(String titleOfArticle) {
        this.titleOfArticle = titleOfArticle;
    }

    public int getIssueOfTheJournal() {
        return issueOfTheJournal;
    }

    public void setIssueOfTheJournal(int issueOfTheJournal) {
        this.issueOfTheJournal = issueOfTheJournal;
    }

    @Override
    public String toString() {
        return "Journal article: " + "\"" + titleOfArticle + '\"' +
                ", in the journal \"" + getTitle() + '\"' +
                ", issue of the journal: " + issueOfTheJournal +
                ", author:'" + getAuthor() + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalArticle that = (JournalArticle) o;
        return issueOfTheJournal == that.issueOfTheJournal &&
                titleOfArticle.equals(that.titleOfArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleOfArticle, issueOfTheJournal);
    }
}
