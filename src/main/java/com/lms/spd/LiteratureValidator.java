package com.lms.spd;

import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LiteratureType;
import com.lms.spd.litfactory.*;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;

import java.util.*;

public class LiteratureValidator {
    private LiteratureServiceImpl literatureServiceImpl = new LiteratureServiceImpl();
    private static final Map<LiteratureType, LitFactory> FACTORIES = Map.of(
            LiteratureType.BOOK, new LBookFactory(),
            LiteratureType.INTERNET_ARTICLE, new LInternetArticleFactory(),
            LiteratureType.JOURNAL_ARTICLE, new LJournalFactory()
    );

    private static LitFactory createLitFactory(LiteratureType type) {
        return FACTORIES.get(type);
    }

    public Literature createLiterature() {
        LitBuilder literatureCreator = createLitFactory(getLiteratureType()).createLitBuilder();
        return literatureCreator.createLiterature();
    }

    private LiteratureType getLiteratureType() {
        LiteratureType typeLit;
        ConsoleMassage.MESSAGE_CHOOSE_LITERATURE_TYPE.printMassage();
        while (true) {
            int number = ConsoleInputValidator.readInt() - 1;
            typeLit = LiteratureType.stream().filter(d -> d.ordinal() == number).findFirst().orElse(null);
            if (typeLit == null) {
                ConsoleMassage.MESSAGE_ERR_UNKNOWN_TYPE.printMassage();
            } else {
                break;
            }
        }
        return typeLit;
    }
//____________________________________________________________________________________________________________________//

    public List<Literature> addLitOrNot() {
        ConsoleMassage.MESSAGE_CHOIOSE_ADD_LIT_CHOOSE.printMassage();
        List<Literature> newLiteratureArr = new ArrayList<>();
        switch (ConsoleInputValidator.readString()) {
            case "+":
                newLiteratureArr.add(literatureServiceImpl.addItem(createLiterature()));
                ConsoleMassage.MESSAGE_Q_ADD_MORE_LIT.printMassage();
                while (!ConsoleInputValidator.readString().equals("-")) {
                    newLiteratureArr.add(literatureServiceImpl.addItem(createLiterature()));
                    ConsoleMassage.MESSAGE_Q_ADD_MORE_LIT.printMassage();
                }
                break;
            case "-":
                break;
            default:
                ConsoleMassage.MESSAGE_ERR_INCORRECT_INPUT.printMassage();
                addLitOrNot();
        }
        return newLiteratureArr;
    }
}
