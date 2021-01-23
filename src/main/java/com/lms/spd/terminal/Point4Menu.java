package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Point4Menu implements ITerminal {

    private LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();
    private LiteratureServiceImpl literatureServiceImpl = new LiteratureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LiteratureValidator literatureValidator = new LiteratureValidator();

    @Override

    public void showContext() {
        ConsoleMassage.MESSAGE_HEAD_MENU_POINT_4.printMassage();
        int numbOfLecture = ConsoleInputValidator.readInt();
        if (numbOfLecture != 0) {
            lectureServiceImpl.setSelectedItem(numbOfLecture);
            if (lectureServiceImpl.getItems().stream().noneMatch(lecture -> lecture.getId() == numbOfLecture)) {
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_LECTURE.printMassage();
                showContext();
            } else {
                ConsoleMassage.MESSAGE_ST_SELECT_LECT.printMassage();
                print.printLectureTable(lectureServiceImpl.getSelectedItem());
                ConsoleMassage.MESSAGE_Q_WHAT_NEXT_ACTIONS.printMassage();
            }
        }
        ConsoleMassage.MESSAGE_MENU_POINT_4.printMassage();
        subMenu2Point4();
    }

    private void subMenu2Point4() {
        switch (ConsoleInputValidator.readInt()) {
            case 1: // 1. --> choose another lecture
                showContext();
                break;
            case 2://  2. --> view the list of literature
                point4_2ViewListOfLit();
                break;
            case 3://  3. --> add new literature
                point4_3AddLit();
                subMenu2Point4();
                break;
            case 4://  4. --> remove literature
                point4_4DeleteLit();
                break;
            case 5://  4. --> show lecture info
                point4_5showLectureInfo();
                break;
            case 6:
                LMSTerminal.startLMS();
                break;
            default:
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
                subMenu2Point4();
                break;
        }
    }

    /*✅*/
    private void point4_2ViewListOfLit() {
        int id = lectureServiceImpl.getSelectedItem().getId();
        print.printListLit(literatureServiceImpl.getLiteraturesBYLectureID(id));
        ConsoleMassage.MESSAGE_MENU_POINT_4.printMassage();
        subMenu2Point4();
    }

    private void point4_3AddLit() {
        List<Literature> newLiteratureArr = new ArrayList<>();
        newLiteratureArr.add(literatureServiceImpl.addItem(literatureValidator.createLiterature()));
        List<Integer> integers = newLiteratureArr.stream().map(Literature::getId).collect(Collectors.toList());
        lectureServiceImpl.addLinkLiteratureLectures(lectureServiceImpl.getSelectedItem().getId(), integers);
        ConsoleMassage.MESSAGE_Q_BOOK_ADDED_WHAT_DO_NEXT.printMassage();
        point4_3Navigate();
    }

    private void point4_3Navigate() {
        ConsoleMassage.MESSAGE_Q_ADD_MORE_LIT_NAV.printMassage();
        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                point4_3AddLit();
                break;
            case "-":
                showContext();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
                point4_3Navigate();
                break;
        }
    }

    private void point4_4DeleteLit() {
        ConsoleMassage.MESSAGE_ENTER_NUMBERS_DEL_BOOK.printMassage();
        int[] indexLit = Util.getStringsNumberLecture(ConsoleInputValidator.readString());
        literatureServiceImpl.removeItems(indexLit);
        ConsoleMassage.MESSAGE_Q_DELETE_AGAIN.printMassage();
        ConsoleMassage.MESSAGE_Q_YES_OR_NO.printMassage();
        subMenuPoint4_4DeleteLit();
    }

    private void subMenuPoint4_4DeleteLit() {
        ConsoleMassage.MESSAGE_Q_YES_OR_NO.printMassage();
        switch (ConsoleInputValidator.readString()) {
            case "+":
                point4_4DeleteLit();
                break;
            case "-":
                showContext();
                break;
            default:
                ConsoleMassage.MESSAGE_ERR_NO_SUCH_ITEM.printMassage();
                subMenuPoint4_4DeleteLit();
                break;
        }
    }

    private void point4_5showLectureInfo() {
        Lecture selectedItem = lectureServiceImpl.getSelectedItem();
        print.showAllLectureInfo(selectedItem);
        ConsoleMassage.MESSAGE_MENU_POINT_4.printMassage();
        subMenu2Point4();
    }
}
