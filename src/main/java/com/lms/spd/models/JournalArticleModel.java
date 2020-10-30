package com.lms.spd.models;

import com.lms.spd.models.interfaces.Literature;

import java.util.Objects;

public class JournalArticleModel implements Literature {
    private String title;
    private String author;
    private String titleOfArticle;
    private int issueOfTheJournal;

    public JournalArticleModel(String titleOfArticle, String author, String titleJournal, int issueOfTheJournal) {
        setAuthor(author);
        setTitle(titleJournal);
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
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
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

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
        if (!(o instanceof JournalArticleModel)) return false;
        JournalArticleModel that = (JournalArticleModel) o;
        return getIssueOfTheJournal() == that.getIssueOfTheJournal() &&
                getTitle().equals(that.getTitle()) &&
                getAuthor().equals(that.getAuthor()) &&
                getTitleOfArticle().equals(that.getTitleOfArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getTitleOfArticle(), getIssueOfTheJournal());
    }
}
