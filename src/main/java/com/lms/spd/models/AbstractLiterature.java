package com.lms.spd.models;


import java.util.Objects;

public abstract class AbstractLiterature {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public abstract void print();

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractLiterature)) return false;
        AbstractLiterature that = (AbstractLiterature) o;
        return getTitle().equals(that.getTitle()) &&
                getAuthor().equals(that.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor());
    }
}

