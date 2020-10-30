package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LiteratureServiceImpl implements LiteratureService {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Literature> literatures = new ArrayList<>();

    @Override
    public ArrayList<Literature> getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(ArrayList<Literature> literatures) {
        this.literatures = literatures;
    }

    @Override
    public Literature createJournal() throws IOException {
        Literature newJournal;
        System.out.println("Enter a title of article");
        String title = reader.readLine();
        if (title.isEmpty()) {
            title = "Unknown";
        }
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        if (author.isEmpty()) {
            author = "Unknown";
        }
        System.out.println("Please enter a titleJournal name or press Enter");
        String titleJournal = reader.readLine();
        if (titleJournal.isEmpty()) {
            titleJournal = "Unknown";
        }
        System.out.println("Please enter a issue of the journal where the article was published");
        int issueOfTheJournal = 0;
        try {
            issueOfTheJournal = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | NullPointerException e) {
            issueOfTheJournal = 0;
        }

        newJournal = new JournalArticleModel(title, author, titleJournal, issueOfTheJournal);
        return newJournal;
    }

    @Override
    public Literature createBook() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of book");
        String title = reader.readLine();
        if (title.isEmpty()) {
            title = "Unknown";
        }
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        if (author.isEmpty()) {
            author = "Unknown";
        }
        System.out.println("Please enter a genre name or press Enter");
        String genre = reader.readLine();
        if (genre.isEmpty()) {
            genre = "Unknown";
        }
        System.out.println("Please enter a year of publication of the book");
        int year = 0;
        try {
            year = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException ignored) {
            //
        }
        newLit = new BookModel(title, author, genre, year);
        return newLit;
    }

    @Override
    public Literature createInternetArticles() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of article");
        String title = reader.readLine();
        if (title.isEmpty()) {
            title = "Unknown";
        }
        System.out.println("Please enter a author name");
        String author = reader.readLine();
        if (author.isEmpty()) {
            author = "Unknown";
        }
        System.out.println("Please enter a url address or press Enter");
        String urlAddress = reader.readLine();
        if (urlAddress.isEmpty()) {
            urlAddress = "Unknown";
        }
        newLit = new InternetArticleModel(title, author, urlAddress);
        return newLit;
    }

    @Override
    public ArrayList<Literature> removeLiterature(int numberLit, ArrayList<Literature> lit) {
        if (lit.size() == 1) {
            lit = (new ArrayList<>());
        } else {
            lit.remove(numberLit - 1);
        }
        return lit;
    }
}
