package com.lms.spd.services.interfaces;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.interfaces.Literature;

import java.io.IOException;
import java.util.List;

public interface LiteratureService {
    List<Literature> getLiteratures();

    void setLiteratures(List<Literature> literatures);

    Literature createJournal(String title, String author, String titleJournal, String issueOfTheJournal2) throws IOException;

    Literature createBook(String title,String author,String genre,String year) throws IOException;

    Literature createInternetArticles(String title,String author,String urlAddress) throws IOException;

    List<Literature> removeLiterature(int numberLit, List<Literature> lit);


}
