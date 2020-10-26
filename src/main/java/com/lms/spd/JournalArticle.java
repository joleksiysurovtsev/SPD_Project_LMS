package com.lms.spd;

import java.util.Objects;

public class JournalArticle extends Literature {
    String titleOfArticle;
    int issueOfTheJournal;

    public JournalArticle(String titleOfArticle, String author, String titleJournal, int issueOfTheJournal) {
        setAuthor(author);
        setTitle(titleJournal);
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
    }

    public JournalArticle(String titleOfArticle, String author, String titleJournal) {
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

    public JournalArticle(String titleOfArticle, String author, int issueOfTheJournal) {
        setAuthor(author);
        setTitle(titleOfArticle);
        this.titleOfArticle = "N/A";
        this.issueOfTheJournal = issueOfTheJournal;
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
    public void print() {
        StringBuilder printString = new StringBuilder();
        if (!titleOfArticle.equals("N/A")) {
            printString.append("Article: ").append(titleOfArticle);
        }

        if (!getTitle().equals("N/A")) {
            printString.append(" In the journal ").append(getTitle());
        }

        if (issueOfTheJournal != 0) {
            printString.append(" Journal №: ").append(issueOfTheJournal);
        }
        System.out.print(printString+"\n");
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
        if (!(o instanceof JournalArticle)) return false;
        if (!super.equals(o)) return false;
        JournalArticle that = (JournalArticle) o;
        return getIssueOfTheJournal() == that.getIssueOfTheJournal() &&
                getTitleOfArticle().equals(that.getTitleOfArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitleOfArticle(), getIssueOfTheJournal());
    }
}
