package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LiteratureServiceImpl implements LiteratureService {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        int issueOfTheJournal = 0;
        try {
            issueOfTheJournal = Integer.parseInt(reader.readLine());
        }catch (NumberFormatException | NullPointerException e){
            //
        }

        newJournal = new JournalArticleModel(titleOfArticle, author);
        if (!titleJournal.isEmpty()) {
            newJournal = new JournalArticleModel(titleOfArticle, author, titleJournal);
        }
        if (titleJournal.isEmpty() && issueOfTheJournal != 0) {
            newJournal = new JournalArticleModel(titleOfArticle, author, issueOfTheJournal);
        }
        if (issueOfTheJournal != 0) {
            newJournal = new JournalArticleModel(titleOfArticle, author, titleJournal, issueOfTheJournal);
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
        int year = 0;
        try {
            year = Integer.parseInt(reader.readLine());
        }catch (NumberFormatException ignored){
            //
        }

        newLit = new BookModel(nameBook, author);
        if (!genre.isEmpty()) {
            newLit = new BookModel(nameBook, author, genre);
        }
        if (genre.isEmpty() && year != 0) {
            newLit = new BookModel(nameBook, author, year);
        }
        if (!genre.isEmpty() && year != 0) {
            newLit = new BookModel(nameBook, author, genre, year);
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
        newLit = new InternetArticleModel(titleOfArticle, author);
        if (!urlAddress.isEmpty()) {
            newLit = new InternetArticleModel(titleOfArticle, author, urlAddress);
        }
        return newLit;
    }

    @Override
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
