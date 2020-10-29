package com.lms.spd.models;

import com.lms.spd.models.interfaces.Literature;

import java.util.Objects;

public class BookModel implements Literature {
    private String title;
    private String author;
    private String genre;
    private int publishedInYear;

    public BookModel(String title, String author) {
        this.genre = "N/A";
        this.publishedInYear = 0;
        setTitle(title);
        setAuthor(author);
    }

    public BookModel(String title, String author, String genre) {
        this.genre = genre;
        this.publishedInYear = 0;
        setTitle(title);
        setAuthor(author);
    }

    public BookModel(String title, String author, String genre, int publishedInYear) {
        this.genre = genre;
        this.publishedInYear = publishedInYear;
        setTitle(title);
        setAuthor(author);
    }

    public BookModel(String nameBook, String author, int year) {
        this.genre = "N/A";
        this.publishedInYear = year;
        setTitle(nameBook);
        setAuthor(author);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishedInYear() {
        return publishedInYear;
    }

    public void setPublishedInYear(int publishedInYear) {
        this.publishedInYear = publishedInYear;
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
    public void print() {
        StringBuilder printString = new StringBuilder("Book: " + getTitle() + " Author: " + getAuthor());
        if (!genre.equals("N/A")) {
            printString.append(" Genre: ").append(genre);
        }
        if (publishedInYear != 0) {
            printString.append(" Year of publishing: ").append(publishedInYear);
        }
        System.out.print(printString + "\n");
    }

    @Override
    public String toString() {
        String publishedInYearStr = "N/A";
        if (this.publishedInYear != 0) {
            publishedInYearStr = String.valueOf(publishedInYear);
        }
        return "Book: " + getTitle() + ", author: '" + getAuthor() + '\'' + " genre '" + genre + '\'' +
                ", published in year " + publishedInYearStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookModel)) return false;
        if (!super.equals(o)) return false;
        BookModel book = (BookModel) o;
        return getPublishedInYear() == book.getPublishedInYear() &&
                getGenre().equals(book.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGenre(), getPublishedInYear());
    }
}
