package com.lms.spd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.interfaces.Literature;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class BookModel implements Literature, Serializable {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("Literature type")
    private LiteratureType type = LiteratureType.BOOK;
    @JsonProperty("Date resource was added")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Calendar dateResourceWasAdded = Calendar.getInstance();

    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("PublishedInYear")
    private int publishedInYear;

    public BookModel() {
    }

    public BookModel(String title, String author, LiteratureType type, Calendar dateResourceWasAdded, String genre, int publishedInYear) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.genre = genre;
        this.publishedInYear = publishedInYear;
    }

    public BookModel(int id, String title, String author, LiteratureType type, Calendar dateResourceWasAdded, String genre, int publishedInYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.dateResourceWasAdded = dateResourceWasAdded;
        this.genre = genre;
        this.publishedInYear = publishedInYear;
    }

    public BookModel(String title, String author, String genre, int publishedInYear, int id) {
        this.genre = genre;
        this.publishedInYear = publishedInYear;
        setTitle(title);
        setAuthor(author);
        this.dateResourceWasAdded = Calendar.getInstance();
        this.id = id;
    }

    public BookModel(String title, String author, String genre, int publishedInYear) {
        this.genre = genre;
        this.publishedInYear = publishedInYear;
        setTitle(title);
        setAuthor(author);
        this.dateResourceWasAdded = Calendar.getInstance();
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
    public Calendar getDateResourceWasAdded() {
        return dateResourceWasAdded;
    }

    @Override
    public void setDateResourceWasAdded(Calendar dateResourceWasAdded) {
        this.dateResourceWasAdded = dateResourceWasAdded;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getTitleOfArticle() {
        return null;
    }

    @Override
    public void setTitleOfArticle(String titleOfArticle) {
        //interface method literature refers to another model
    }

    @Override
    public int getIssueOfTheJournal() {
        return 0;
    }

    @Override
    public void setIssueOfTheJournal(int issueOfTheJournal) {
        //interface method literature refers to another model
    }

    @Override
    public String getUrlAddress() {
        return null;
    }

    @Override
    public void setUrlAddress(String urlAddress) {
        //interface method literature refers to another model
    }

    public int getPublishedInYear() {
        return publishedInYear;
    }

    public void setPublishedInYear(int publishedInYear) {
        this.publishedInYear = publishedInYear;
    }

    @Override
    public String print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        StringBuilder printString = new StringBuilder(" Book: " + getTitle() + " Author: " + getAuthor());
        if (!genre.equals("Unknown")) {
            printString.append(" Genre: ").append(genre);
        }
        if (publishedInYear != 0) {
            printString.append(" Publishing in: ").append(publishedInYear).append("year");
        }
        printString.append("\t" + " Date the resource was added: ")
                .append(sdf.format(getDateResourceWasAdded().getTime())).append(" ID ").append(getId());
        return printString.toString();
    }

    @Override
    public String toString() {
        String publishedInYearStr;
        if (this.publishedInYear != 0) {
            publishedInYearStr = String.valueOf(publishedInYear);
        } else {
            publishedInYearStr = "N/A";
        }
        return "Book: " + getTitle() + ", author: '" + getAuthor() + '\'' + " genre '" + genre + '\'' +
                ", published in year " + publishedInYearStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookModel)) {
            return false;
        }
        BookModel bookModel = (BookModel) o;
        return getPublishedInYear() == bookModel.getPublishedInYear() &&
                getTitle().equals(bookModel.getTitle()) &&
                getAuthor().equals(bookModel.getAuthor()) &&
                getGenre().equals(bookModel.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getGenre(), getPublishedInYear());
    }
}
