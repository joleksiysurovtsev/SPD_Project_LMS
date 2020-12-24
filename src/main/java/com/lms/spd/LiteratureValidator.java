package com.lms.spd;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.litfactory.*;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;

import java.util.*;

public class LiteratureValidator {
    private LiteratureServiceImpl literatureServiceImpl = new LiteratureServiceImpl();

    private static final Map<LiteratureType, LitFactory> factories = Map.of(
            LiteratureType.BOOK, new LBookFactory(),
            LiteratureType.INTERNET_ARTICLE, new LInternetArticleFactory(),
            LiteratureType.JOURNAL_ARTICLE, new LJournalFactory()

    );

    private static LitFactory createLitFactory(LiteratureType type){
        return factories.get(type);
    }

    public Literature createLiterature() {
        LitBuilder literatureCreator= createLitFactory(getLiteratureType()).createLitBuilder();
        return literatureCreator.createLiterature();
    }

    private LiteratureType getLiteratureType() {
        LiteratureType typeLit;
        System.out.println("Please, choose literature type: \n" + LiteratureType.toListString());
        while (true) {
            int number = ConsoleInputValidator.readInt() - 1;
            typeLit = LiteratureType.stream().filter(d -> d.ordinal() == number).findFirst().orElse(null);
            if (typeLit == null) {
                System.out.println("Unknown type: try again");
            } else {
                break;
            }
        }
        return typeLit;
    }
//____________________________________________________________________________________________________________________//

    List<Literature> addLitOrNot() {
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        List<Literature> newLiteratureArr = new ArrayList<>();
        switch (ConsoleInputValidator.readString()) {
            case "+":
                literatureServiceImpl.addLiterature(createLiterature(), newLiteratureArr);
                System.out.println("Add more literature? if not enter minus");
                while (!ConsoleInputValidator.readString().equals("-")) {
                    literatureServiceImpl.addLiterature(createLiterature(), newLiteratureArr);
                    System.out.println("Add more literature? if not enter minus");
                }
                break;
            case "-":
                break;
            default:
                System.out.println("Something wrong");
                addLitOrNot();
        }
        return newLiteratureArr;
    }
}
