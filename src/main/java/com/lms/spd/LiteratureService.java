package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;

public class LiteratureService {
    BufferedReader reader = LMSTerminal.reader;
    Literature[] literatures = new Literature[0];

    public Literature[] getLiteratures() {
        return literatures;
    }

    public void setLiteratures(Literature[] literatures) {
        this.literatures = literatures;
    }

    public Literature createJournal() throws IOException {
        System.out.println("Enter a title of article");
        String titleOfArticle = reader.readLine();
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        System.out.println("Please enter a titleJournal name or press Enter");
        String titleJournal;
        if (!(titleJournal = reader.readLine()).isEmpty()) {
            return new JournalArticle(titleOfArticle, author, titleJournal);
        }
        System.out.println("Please enter a issue of the journal where the article was published");
        int issueOfTheJournal = Integer.parseInt(reader.readLine());
        if (issueOfTheJournal != 0) {
            return new JournalArticle(titleOfArticle, author, titleJournal, issueOfTheJournal);
        }
        return new JournalArticle(titleOfArticle, author);
    }

    public Literature createBook() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of book");
        String nameBook = LMSTerminal.reader.readLine();
        System.out.println("Please enter a author name");
        String author = LMSTerminal.reader.readLine();
        System.out.println("Please enter a genre name or press Enter");
        String genre = reader.readLine();
        if (!genre.isEmpty()) {
            System.out.println("Please enter a year of publication of the book");
            int year = 0;
            while ((year = Integer.parseInt(LMSTerminal.reader.readLine())) != 0) {
                return newLit = new Book(nameBook, author, genre, year);
            }
            return newLit = new Book(nameBook, author, genre);
        }
        return newLit = new Book(nameBook, author);
    }

    public Literature createInternetArticles() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of article");
        String titleOfArticle = LMSTerminal.reader.readLine();
        System.out.println("Please enter a author name");
        String author = LMSTerminal.reader.readLine();
        System.out.println("Please enter a url address or press Enter");
        String urlAddress;
        while ((urlAddress = LMSTerminal.reader.readLine()).isEmpty()) {
            return newLit = new JournalArticle(titleOfArticle, author, urlAddress);
        }
        return newLit = new JournalArticle(titleOfArticle, author);
    }
}
