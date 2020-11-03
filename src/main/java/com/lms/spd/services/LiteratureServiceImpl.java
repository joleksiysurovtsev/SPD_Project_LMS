package com.lms.spd.services;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiteratureServiceImpl implements LiteratureService {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<Literature> literatures = new ArrayList<>();
    String empty = "Unknown";

    @Override
    public List<Literature> getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(List<Literature> literatures) {
        this.literatures = literatures;
    }

    private void printMessages(int message) {
        if (message == 1) {System.out.println("Enter a title");}
        if (message == 2) {System.out.println("Please enter a author name");}
        if (message == 3) {System.out.println("Please enter a titleJournal name or press Enter");}
        if (message == 4) {System.out.println("Please enter a issue of the journal where the article was published");}
        if (message == 5) {System.out.println("Please enter a url address or press Enter");}
        if (message == 6) {System.out.println("Please enter a genre name or press Enter");}
        if (message == 7) {System.out.println("Please enter a year of publication of the book");}
    }

    @Override
    public Literature inputData(LiteratureType type) throws IOException {
        printMessages(1);
        String title = reader.readLine();
        printMessages(2);
        String author = reader.readLine();
        Literature lit;
        switch (type) {
            case JOURNAL_ARTICLE:
                printMessages(3);
                String titleJournal = reader.readLine();
                printMessages(4);
                String issueOfTheJournal = reader.readLine();
                lit = createJournal(title, author, titleJournal, issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                printMessages(5);
                String urlAddress = reader.readLine();
                lit = createInternetArticles(title, author, urlAddress);
                break;
            case BOOK:
                printMessages(6);
                String genre = reader.readLine();
                printMessages(7);
                String year = reader.readLine();
                lit = createBook(title, author, genre, year);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        lit.setType(type);
        return lit;
    }

    @Override
    public Literature createJournal(String title, String author, String titleJournal, String issueOfTheJournal) {
        if (title.isEmpty()) {title = empty;}
        if (author.isEmpty()) {author = empty;}
        if (titleJournal.isEmpty()) {titleJournal = empty;}
        int issueOfTheJour;
        try {issueOfTheJour = Integer.parseInt(issueOfTheJournal);}
        catch (NumberFormatException | NullPointerException e) {issueOfTheJour = 0;}
        return new JournalArticleModel(title, author, titleJournal, issueOfTheJour);
    }


    @Override
    public Literature createBook(String title, String author, String genre, String year) {
        if (title.isEmpty()) {title = empty;}
        if (author.isEmpty()) {author = empty;}
        if (genre.isEmpty()) {genre = empty;}
        int years;
        try { years = Integer.parseInt(year);}
        catch (NumberFormatException ignored) {years = 0;}
        return new BookModel(title, author, genre, years);
    }

    @Override
    public Literature createInternetArticles(String title, String author, String urlAddress) {
        if (title.isEmpty()) {title = empty;}
        if (author.isEmpty()) {author = empty;}
        if (urlAddress.isEmpty()) {urlAddress = empty;}
        return new InternetArticleModel(title, author, urlAddress);
    }

    @Override
    public List<Literature> removeLiterature(int numberLit, List<Literature> lit) {
        if (lit.size() == 1) {
            lit = Collections.emptyList();// (new ArrayList<>() C);
        } else {
            lit.remove(numberLit - 1);
        }
        return lit;
    }
}
