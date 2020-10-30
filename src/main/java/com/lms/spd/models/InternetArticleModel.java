package com.lms.spd.models;

import com.lms.spd.models.interfaces.Literature;

import java.util.Objects;

public class InternetArticleModel implements Literature {
    private String title;
    private String author;
    private String urlAddress;

    public InternetArticleModel(String title, String author, String urlAddress) {
        setAuthor(author);
        setTitle(title);
        this.urlAddress = urlAddress;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
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
        StringBuilder printString = new StringBuilder("Title: " + getTitle() + " Author: " + getAuthor());
        if (!urlAddress.equals("N/A")) {
            printString.append(" Web address: ").append(urlAddress);
        }
        System.out.print(printString + "\n");
    }

    @Override
    public String toString() {
        return "Internet articles: " + " " + getTitle() + ", author= " + getAuthor() + " URL " + urlAddress;
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
