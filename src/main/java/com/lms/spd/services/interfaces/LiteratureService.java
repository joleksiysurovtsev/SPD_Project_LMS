package com.lms.spd.services.interfaces;

import com.lms.spd.models.AbstractLiterature;

import java.io.IOException;

public interface LiteratureService {
    AbstractLiterature[] getLiteratures();

    void setLiteratures(AbstractLiterature[] literatures);

    AbstractLiterature createJournal() throws IOException;

    AbstractLiterature createBook() throws IOException;

    AbstractLiterature createInternetArticles() throws IOException;
}
