package com.lms.spd.models;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.interfaces.Literature;

import java.util.Objects;

public class InternetArticleModel implements Literature {
    private String title;
    private String author;
    private String urlAddress;

    private LiteratureType type;

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
    public LiteratureType getType() {
        return type;
    }

    @Override
    public void setType(LiteratureType type) {
        this.type = type;
    }

    @Override
    public String print() {
        StringBuilder printString = new StringBuilder(" Internet articles: " + getTitle() + " Author: " + getAuthor());
        if (!urlAddress.equals("Unknown")) {
            printString.append(" Web address: ").append(urlAddress);
        }
        return printString.toString();
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
