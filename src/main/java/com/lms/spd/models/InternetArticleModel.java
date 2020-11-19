package com.lms.spd.models;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.interfaces.Literature;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class InternetArticleModel implements Literature {
    private String title;
    private String author;
    private String urlAddress;
    private Calendar dateResourceWasAdded;
    private LiteratureType type;
    private int id;

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
    public String print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        StringBuilder printString = new StringBuilder(" Internet articles: " + getTitle() + " Author: " + getAuthor()+ " Date the resource was added: " + sdf.format(getDateResourceWasAdded().getTime())).append(" ID "+ getId());
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
