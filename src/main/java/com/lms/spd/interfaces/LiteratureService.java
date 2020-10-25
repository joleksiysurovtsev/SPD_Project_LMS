package com.lms.spd.interfaces;

import com.lms.spd.Literature;

import java.io.IOException;

public interface LiteratureService {
    Literature[] getLiteratures();

    void setLiteratures(Literature[] literatures);

    Literature createJournal() throws IOException;

    Literature createBook() throws IOException;

    Literature createInternetArticles() throws IOException;
}
