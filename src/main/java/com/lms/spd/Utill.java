package com.lms.spd;

import java.util.Arrays;

public class Utill {
    /**
     * returns numbers arr from strings
     */
    public static int[] getStringsNumberLecture(String s) {
        int[] arr = null;
        try {
            arr = Arrays.stream(s.replaceAll("[^0-9,]", "").split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect input");
        }
        return arr;
    }


}
