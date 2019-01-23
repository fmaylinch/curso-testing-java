package com.platzi.javatests.util;

public class StringUtilTest {

    public static void main(String[] args) {

        assertEquals(StringUtil.repeat("hola", 3), "holaholahola");
        assertEquals(StringUtil.repeat("hola", 1), "hola");
    }

    private static void assertEquals(String actual, String expected) {

        if (!actual.equals(expected)) {
            throw new RuntimeException(actual + " is not equal to expected " + expected);
        }
    }
}