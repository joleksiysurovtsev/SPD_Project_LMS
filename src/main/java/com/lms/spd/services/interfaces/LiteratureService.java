package com.lms.spd.services.interfaces;

import com.lms.spd.models.interfaces.Literature;

import java.io.IOException;
import java.util.ArrayList;

public interface LiteratureService {
    ArrayList<Literature> getLiteratures();

    void setLiteratures(ArrayList<Literature> literatures);

    Literature createJournal() throws IOException;

    Literature createBook() throws IOException;

    Literature createInternetArticles() throws IOException;

    ArrayList<Literature> removeLiterature(int numberLit, ArrayList<Literature> lit);
}
