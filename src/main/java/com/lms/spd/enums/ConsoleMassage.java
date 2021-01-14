package com.lms.spd.enums;


public enum ConsoleMassage {

    MESSAGE_HEAD_START_MENU("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" +
            "\"" + ": learning management system" + "\u001B[0m\n" +
            "\"Please make your choice from the offered options\"\n"
            + "1. Display lectures (number and title)\n" + "2. Add a new lecture\n"
            + "3. Delete a lecture by its ID\n" + "4. Choose a lecture by its ID\n"
            + "0. \u001B[31mExit.\n\u001B[0m"),

    MESSAGE_HEAD_MENU_POINT_1("\u001b[36;1m\"+\"\u001B[0m Display all lectures\n" + "\u001b[31;1m\"-\" \u001B[0mSpecifically some by ID\n"
            + "\u001B[32m\"SMALL\"\u001B[0m To preview lectures\n" + "\u001B[35m\"TYPE\"\u001B[0m Display lectures of a certain type \n"
            + "\u001B[36m\"DATE\"\u001B[0m Display lectures by curend date\n"
            + "\u001B[34m\"TYPE AND DATE\"\u001B[0m Display lectures by curend date\n"
            + "\u001B[31m\"EXIT\"\u001B[0m To go to the main menu"),
    MESSAGE_HEAD_MENU_POINT_3("Please enter the ID of the lecture if you want to delete one or more comma separated "),
    MESSAGE_HEAD_MENU_POINT_4("Enter the number of the lecture, " + "information about which you want to see " + "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m"),
    MESSAGE_MENU_POINT_4("\"1. --> choose another lecture\n" + "\"2. --> view the list of literature\n" + "\"3. --> add new literature\n" + "\"4. --> remove literature\n" + "\"5. --> view all lecture information\n" + "\"6. --> exit to the main menu"),

    MESSAGE_ENTER_TITLE("Please enter a title:"),
    MESSAGE_ENTER_TITLE_JOURNAL("Please enter a titleJournal name or press Enter:"),
    MESSAGE_ENTER_TITLE_LECTURE("Please enter the title of the lecture:"),
    MESSAGE_ENTER_AUTHOR("Please enter a author name:"),
    MESSAGE_ENTER_ISSUE_OF_THE_JOURNAL("Please enter a issue of the journal where the article was published:"),
    MESSAGE_ENTER_URL_ADDRESS("Please enter a url address or press Enter:"),
    MESSAGE_ENTER_GENRE_NAME("Please enter a genre name or press Enter"),
    MESSAGE_ENTER_YEAR_OF_PUBLICATION("Please enter a year of publication of the book"),
    MESSAGE_ENTER_LECTURE_DURATION("Please enter the lecture duration"),
    MESSAGE_ENTER_LECTURER_NAME("Please enter lecturer name"),
    MESSAGE_ENTER_NUMBERS_SEP("Please enter numbers separated by commas"),
    MESSAGE_ENTER_NUMBERS_DEL_BOOK("Please enter the number of the book you want to delete"),


    MESSAGE_CHOOSE_LITERATURE_TYPE("Please, choose literature type: \n" + LiteratureType.toListString()),
    MESSAGE_CHOOSE_LECTURE_TYPE("Please, choose lecture type:: \n" + LectureType.toListString()),
    MESSAGE_CHOIOSE_ADD_LIT_CHOOSE("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO"),


    MESSAGE_ST_SELECT_LECT("Selected lecture: "),

    MESSAGE_ERR_NO_SUCH_ITEM("There is no such item in the menu, let's try again"),
    MESSAGE_ERR_NO_SUCH_LECTURE("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again"),
    MESSAGE_ERR_YOU_MUST_ENTER_EITHER("You must enter either \"0\" or EXIT"),
    MESSAGE_ERR_YOU_MUST_ENTER_EITHER2("Please make the right choice, you must enter either \"+\" \"-\" or EXIT"),
    MESSAGE_ERR_INCORRECT_INPUT("Incorrect input"),
    MESSAGE_ERR_UNKNOWN_TYPE("Unknown type: try again"),
    MESSAGE_ERR_LITERATURE_UNDER_THIS_NUMBER("Literature under this number do not exist \n try again"),
    MESSAGE_ERR_LITERATURE_ALREADY("This literature is already there"),

    MESSAGE_Q_WHAT_TO_DO_NEXT_2("What to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program"),
    MESSAGE_Q_DELETE_ANOTHER_ONE("Do you want to delete another one? \n" + "if yes then enter " + "\u001B[31m" + "\"+\" " + "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" + "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program"),
    MESSAGE_Q_WHAT_TO_DO_NEXT("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program "),
    MESSAGE_Q_ENTER_LECTURE("Entering a new lecture?"),
    MESSAGE_Q_ADD_MORE_LIT("Add more literature? if not enter minus"),
    MESSAGE_Q_DELETE_AGAIN("\"Successfully\" Delete again ?"),
    MESSAGE_Q_YES_OR_NO("\"+\" YES or \"-\" NO"),
    MESSAGE_Q_WHAT_NEXT_ACTIONS("What are the next actions?"),
    MESSAGE_Q_ADD_MORE_LIT_NAV("Add more ? if YES then enter \"+\" if NOT then \"-\" you will return to the lecture selection menu, to complete the work, exit "),
    MESSAGE_Q_BOOK_ADDED_WHAT_DO_NEXT("Book added what to do next?");

    private String massage;

    ConsoleMassage(String massage) {
        this.massage = massage;
    }

    public void printMassage() {
        System.out.println(this.massage);
    }
}
