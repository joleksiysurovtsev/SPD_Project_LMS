package com.lms.spd.services.interfaces;

import com.lms.spd.models.interfaces.Literature;

import java.io.IOException;

public interface LiteratureService {
    Literature[] getLiteratures();

    void setLiteratures(Literature[] literatures);

    Literature createJournal() throws IOException;

    Literature createBook() throws IOException;

    Literature createInternetArticles() throws IOException;

    Literature[] removeLiterature(int numberLit, Literature[] lit);
}
