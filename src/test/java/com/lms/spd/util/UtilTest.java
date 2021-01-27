package com.lms.spd.util;

import com.lms.spd.utils.Util;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    @Order(39)
    @DisplayName("getStringsNumberLecture")
    @Description("Case 1: The method should return an array of two numbers")
    void getStringsNumberLecture() {
        int[] expected = {1, 2};
        assertArrayEquals(expected, Util.getStringsNumberLecture("1text between two numbers,2"));
    }

    @Test
    @Order(40)
    @DisplayName("getStringsNumberLecture 2")
    @Description("Case 2: The method should return null")
    void getStringsNumberLecture2() {
        int[] expected = {};
        assertArrayEquals(expected, Util.getStringsNumberLecture(""));
    }
}