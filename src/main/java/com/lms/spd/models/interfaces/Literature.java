package com.lms.spd.models.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lms.spd.enums.LiteratureType;

import java.util.Calendar;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@ class")
public interface Literature {

    String getTitle();

    void setTitle(String title);

    String getAuthor();

    void setAuthor(String author);

    LiteratureType getType();

    void setType(LiteratureType type);

    String print();

    Calendar getDateResourceWasAdded();

    void setDateResourceWasAdded(Calendar dateResourceWasAdded);

    int getId();

    void setId(int id);

    String getGenre();

    void setGenre(String genre);

    String getTitleOfArticle();

    void setTitleOfArticle(String titleOfArticle);

    int getIssueOfTheJournal();

    void setIssueOfTheJournal(int issueOfTheJournal);

    String getUrlAddress();

    void setUrlAddress(String urlAddress);

    int getPublishedInYear();

    void setPublishedInYear(int publishedInYear);

}
