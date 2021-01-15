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

public class InternetArticleModel implements Literature, Serializable {
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

    @JsonProperty("URL")
    private String urlAddress;

    public InternetArticleModel() {
    }

    public InternetArticleModel(LiteratureType type, String title, String author, String urlAddress, Calendar dateResourceWasAdded) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.urlAddress = urlAddress;
    }

    public InternetArticleModel(int id, String title, String author, LiteratureType type, Calendar dateResourceWasAdded, String urlAddress) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.urlAddress = urlAddress;
    }

    public InternetArticleModel(String title, String author, String urlAddress) {
        setAuthor(author);
        setTitle(title);
        this.urlAddress = urlAddress;
        this.dateResourceWasAdded = GregorianCalendar.getInstance();
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public int getPublishedInYear() {
        return 0;
    }

    @Override
    public void setPublishedInYear(int publishedInYear) {

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

    @Override
    public String getTitleOfArticle() {
        return null;
    }

    @Override
    public void setTitleOfArticle(String titleOfArticle) {

    }

    @Override
    public int getIssueOfTheJournal() {
        return 0;
    }

    @Override
    public void setIssueOfTheJournal(int issueOfTheJournal) {

    }

    @Override
    public String print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        StringBuilder printString = new StringBuilder(" Internet articles: " + getTitle()
                + " Author: " + getAuthor() + " Date the resource was added: "
                + sdf.format(getDateResourceWasAdded().getTime())).append(" ID ").append(getId());
        if (!urlAddress.equals("Unknown")) {
            printString.append(" Web address: ").append(urlAddress);
        }
        return printString.toString();
    }

    @Override
    public Calendar getDateResourceWasAdded() {
        return dateResourceWasAdded;
    }

    @Override
    public void setDateResourceWasAdded(Calendar dateResourceWasAdded) {
        this.dateResourceWasAdded = dateResourceWasAdded;
    }

    @Override
    public String toString() {
        return " Internet articles: " + " " + getTitle() + ", author= " + getAuthor() + " URL " + urlAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternetArticleModel)) return false;
        InternetArticleModel that = (InternetArticleModel) o;
        return getTitle().equals(that.getTitle()) &&
                getAuthor().equals(that.getAuthor()) &&
                getUrlAddress().equals(that.getUrlAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getUrlAddress());
    }
}
