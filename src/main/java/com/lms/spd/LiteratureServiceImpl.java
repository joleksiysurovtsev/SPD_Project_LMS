package com.lms.spd;

import com.lms.spd.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class LiteratureServiceImpl implements LiteratureService {
    BufferedReader reader = LMSTerminal.reader;
    Literature[] literatures = new Literature[0];

    @Override
    public Literature[] getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(Literature[] literatures) {
        this.literatures = literatures;
    }


    @Override
    public Literature createJournal() throws IOException {
        Literature newJournal;
        System.out.println("Enter a title of article");
        String titleOfArticle = reader.readLine();
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        System.out.println("Please enter a titleJournal name or press Enter");
        String titleJournal = reader.readLine();
        System.out.println("Please enter a issue of the journal where the article was published");
        int issueOfTheJournal = Integer.parseInt(reader.readLine());

        newJournal = new JournalArticle(titleOfArticle, author);

        if (!titleJournal.isEmpty()) {
            newJournal = new JournalArticle(titleOfArticle, author, titleJournal);
        }
        if (titleJournal.isEmpty() && issueOfTheJournal != 0) {
            newJournal = new JournalArticle(titleOfArticle, author, issueOfTheJournal);
        }

        if (issueOfTheJournal != 0) {
            newJournal = new JournalArticle(titleOfArticle, author, titleJournal, issueOfTheJournal);
        }
        return newJournal;
    }

    @Override
    public Literature createBook() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of book");
        String nameBook = reader.readLine();
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        System.out.println("Please enter a genre name or press Enter");
        String genre = reader.readLine();
        System.out.println("Please enter a year of publication of the book");
        int year = Integer.parseInt(reader.readLine());

        newLit = new Book(nameBook, author);

        if (!genre.isEmpty()) {
            newLit = new Book(nameBook, author, genre);
        }
        if (genre.isEmpty() && year != 0) {
            newLit = new Book(nameBook, author, year);
        }
        if (!genre.isEmpty() && year != 0) {
            newLit = new Book(nameBook, author, genre, year);
        }
        return newLit;
    }

    @Override
    public Literature createInternetArticles() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of article");
        String titleOfArticle = reader.readLine();
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        System.out.println("Please enter a url address or press Enter");
        String urlAddress = reader.readLine();

        newLit = new InternetArticles(titleOfArticle, author);
        if (!urlAddress.isEmpty()) {
            newLit = new InternetArticles(titleOfArticle, author, urlAddress);
        }
        return newLit;
    }

    public Literature[] removeLiterature(int numberLit, Literature[] lit) {
        if (lit.length == 1) {
            lit = (new Literature[0]);
        } else {
            Literature[] literature = lit;
            Literature litTuDel = literature[numberLit - 1];
            lit = (Arrays.stream(literature).filter(x -> !(x == litTuDel)).toArray(Literature[]::new));
        }
        return lit;
    }


}
