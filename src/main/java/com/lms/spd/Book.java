package com.lms.spd;

import java.util.Objects;

public class Book extends Literature {

    private String genre;
    private int publishedInYear;

    public Book(String title, String author) {
        this.genre = "N/A";
        this.publishedInYear = 0;
        setTitle(title);
        setAuthor(author);
    }

    public Book(String title, String author, String genre) {
        this.genre = genre;
        this.publishedInYear = 0;
        setTitle(title);
        setAuthor(author);
    }

    public Book(String title, String author, String genre, int publishedInYear) {
        this.genre = genre;
        this.publishedInYear = publishedInYear;
        setTitle(title);
        setAuthor(author);
    }

    public Book(String nameBook, String author, int year) {
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
    public void print() {
        StringBuilder printString = new StringBuilder("Book: "+ getTitle() + " Author: "+ getAuthor());
        if (!genre.equals("N/A")){
            printString.append(" Genre: ").append(genre);
        }
        if (publishedInYear != 0){
            printString.append(" Year of publishing: ").append(publishedInYear);
        }
        System.out.println(printString);
    }

    @Override
    public String toString() {
        String publishedInYearStr = "N/A";
        if (this.publishedInYear != 0) {
            publishedInYearStr = String.valueOf(publishedInYear);
        }
        return "Book: " + getTitle() + '\'' +
                ", author: '" + getAuthor() + '\'' +
                "genre'" + genre + '\'' +
                ", published in year " + publishedInYearStr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishedInYear == book.publishedInYear &&
                genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, publishedInYear);
    }
}
