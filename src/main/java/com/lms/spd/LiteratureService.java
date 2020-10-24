package com.lms.spd;

import java.io.IOException;

public class LiteratureService {
    Literature[] literatures = new Literature[0];

    public Literature[] getLiteratures() {
        return literatures;
    }

    public void setLiteratures(Literature[] literatures) {
        this.literatures = literatures;
    }

    //__________________________________________________________________________________________________________________//
    Literature[] addLitOrNot(LMSTerminal lmsTerminal) throws IOException {
        Literature[] arrAddLit = new Literature[1];
        switch (LMSTerminal.reader.readLine()) {
            case "+":
                Literature[] newArrAddLit = new Literature[arrAddLit.length + 1];
                System.arraycopy(arrAddLit, 0, newArrAddLit, 0, arrAddLit.length - 1);
                System.out.println("what type of literature do you want to add ?");
                System.out.println("1.Book, 2.Journal article, 3.Internet article");
                int typeOfLit = Integer.parseInt(LMSTerminal.reader.readLine());
                if (typeOfLit == 1) {
                    Literature addLit = createBook();
                    arrAddLit[arrAddLit.length - 1] = addLit;
                }
                if (typeOfLit == 2) {
                    Literature addLit = createJournal();
                    arrAddLit[arrAddLit.length - 1] = addLit;
                }
                if (typeOfLit == 3) {
                    Literature addLit = createInternetArticles();
                    arrAddLit[arrAddLit.length - 1] = addLit;
                }
                break;
            case "-":
                break;
            default:
                System.out.println("Something wrong");
                addLitOrNot(lmsTerminal);
        }
        return arrAddLit;
    }

    public Literature createJournal() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of article");
        String titleOfArticle = LMSTerminal.reader.readLine();
        System.out.println("Please enter a author name");
        String author = LMSTerminal.reader.readLine();
        System.out.println("Please enter a titleJournal name or press Enter");
        String titleJournal;
        while (!(titleJournal = LMSTerminal.reader.readLine()).isEmpty()) {
            return newLit = new JournalArticle(titleOfArticle, author, titleJournal);
        }
        System.out.println("Please enter a issue of the journal where the article was published");
        int issueOfTheJournal = 0;
        while ((issueOfTheJournal = Integer.parseInt(LMSTerminal.reader.readLine())) != 0) {
            return newLit = new JournalArticle(titleOfArticle, author, titleJournal, issueOfTheJournal);
        }
        return newLit = new JournalArticle(titleOfArticle, author);
    }

    public Literature createBook() throws IOException {
        Literature newLit;
        System.out.println("Enter a title of book");
        String nameBook = LMSTerminal.reader.readLine();
        System.out.println("Please enter a author name");
        String author = LMSTerminal.reader.readLine();
        System.out.println("Please enter a genre name or press Enter");
        String genre;
        while (!(genre = LMSTerminal.reader.readLine()).isEmpty()) {
            System.out.println("Please enter a year of publication of the book");
            int year = 0;
            while ((year = Integer.parseInt(LMSTerminal.reader.readLine())) != 0) {
                return newLit = new Book(nameBook, author, genre, year);
            }
            return newLit = new Book(nameBook, author, genre);
        }
        return newLit = new Book(nameBook, author);
    }

    private Literature createInternetArticles() throws IOException {
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
