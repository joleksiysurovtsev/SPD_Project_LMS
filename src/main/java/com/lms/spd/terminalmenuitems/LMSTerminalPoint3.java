package com.lms.spd.terminalmenuitems;

import com.lms.spd.LMSTerminal;
import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class LMSTerminalPoint3 {
    public LMSTerminalPoint3() throws IOException {
        point3MainMenuRemovalLecture();
    }

    /**
     * point 3 main menu: method deleting the lecture list
     */


    private void point3MainMenuRemovalLecture() throws IOException {
        System.out.println("Please enter the number of the lecture if you want to delete one or more comma separated ");
        String numbRemovalLecture = null;
        try {
            numbRemovalLecture = LMSTerminal.reader.readLine();
        } catch (IOException e) {
            point3MainMenuRemovalLecture();
        }
        assert numbRemovalLecture != null;
        String[] lectureRemove = stringToDeleteLecture(numbRemovalLecture);
        LMSTerminal.lectureServiceImpl.removeLectures(lectureRemove);
        subMenuRemovalLecture();

    }


    public String[] stringToDeleteLecture(String lectureRemove) {
        String[] numbDeletedLect = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, numbDeletedLect.length).forEach(i -> numbDeletedLect[i] = numbDeletedLect[i].replaceAll("[a-zA-Zа]*", ""));
        String[] numbToDisplay = Arrays.stream(numbDeletedLect).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures: ");
        boolean flag = true;
        for (String item : numbToDisplay) {
            for (Lecture value : LMSTerminal.lectureServiceImpl.getLectures()) {
                int numb = value.getNumberOfLecture();
                if (numb == Integer.parseInt(item)) {
                    flag = false;
                    stringContains.append(" ").append(item).append(" ");
                    break;
                }
            }
        }
        stringContains.append(!flag ? "successfully removed the rest are missing." : "are missing.");
        System.out.println(stringContains.toString());
        return numbToDisplay;
    }

    private void subMenuRemovalLecture() throws IOException {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");

        switch (LMSTerminal.reader.readLine().toUpperCase()) {
            case "+":
                point3MainMenuRemovalLecture();
                break;
            case "-":
                LMSTerminal.startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                subMenuRemovalLecture();
                break;
        }
    }

}
