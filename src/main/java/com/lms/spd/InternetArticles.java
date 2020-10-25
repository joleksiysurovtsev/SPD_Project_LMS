package com.lms.spd;

import java.util.Objects;

public class InternetArticles extends Literature {
    String urlAddress;

    public InternetArticles(String title, String author, String urlAddress) {
        setAuthor(author);
        setTitle(title);
        this.urlAddress = urlAddress;
    }

    public InternetArticles(String titleOfArticle, String author) {
        setAuthor(author);
        setTitle(titleOfArticle);
        this.urlAddress = "N/A";
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public void print() {
        StringBuilder printString = new StringBuilder("Title: "+ getTitle() + " Author: "+ getAuthor());
        if (!urlAddress.equals("N/A")){
            printString.append(" Web address: ").append(urlAddress);
        }
        System.out.println(printString);
    }

    @Override
    public String toString() {
        return "Internet articles: " +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                "urlAdrres='" + urlAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetArticles that = (InternetArticles) o;
        return urlAddress.equals(that.urlAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlAddress);
    }
}
