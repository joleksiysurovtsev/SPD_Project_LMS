package com.lms.spd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.interfaces.Literature;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class JournalArticleModel implements Literature, Serializable {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("Literature type")
    private LiteratureType type;
    @JsonProperty("Date resource was added")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Calendar dateResourceWasAdded;

    @JsonProperty("Title of article")
    private String titleOfArticle;
    @JsonProperty("Issue of the journal")
    private int issueOfTheJournal;

    public JournalArticleModel() { }

    public JournalArticleModel(String title, String author, LiteratureType type, Calendar dateResourceWasAdded, String titleOfArticle, int issueOfTheJournal) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
    }

    @Override
    public Calendar getDateResourceWasAdded() {
        return dateResourceWasAdded;
    }

    @Override
    public void setDateResourceWasAdded(Calendar dateResourceWasAdded) {
        this.dateResourceWasAdded = dateResourceWasAdded;
    }

    public JournalArticleModel(int id, String title, String author, LiteratureType type, Calendar dateResourceWasAdded, String titleOfArticle, int issueOfTheJournal) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
    }

    public JournalArticleModel(String titleOfArticle, String author, String titleJournal, int issueOfTheJournal) {
        setAuthor(author);
        setTitle(titleJournal);
        this.titleOfArticle = titleOfArticle;
        this.issueOfTheJournal = issueOfTheJournal;
        this.dateResourceWasAdded = GregorianCalendar.getInstance();
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

    @Override
    public LiteratureType getType() {
        return type;
    }

    @Override
    public void setType(LiteratureType type) {
        this.type = type;
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
    public String getUrlAddress() {
        return null;
    }

    @Override
    public void setUrlAddress(String urlAddress) {

    }

    @Override
    public int getPublishedInYear() {
        return 0;
    }

    @Override
    public void setPublishedInYear(int publishedInYear) {

    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getGenre() {
        return null;
    }

    @Override
    public void setGenre(String genre) {

    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    @Override
    public String print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        StringBuilder printString = new StringBuilder();
        printString.append(" Article: ").append(titleOfArticle);
        if (!getTitle().equals("Unknown")) {
            printString.append(" In the journal ").append(getTitle());
        }
        if (issueOfTheJournal != 0) {
            printString.append(" Journal №: ").append(issueOfTheJournal);
        }
        printString.append(" ").append(sdf.format(getDateResourceWasAdded().getTime())).append(" ID ").append(getId());

        return printString.toString();
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
