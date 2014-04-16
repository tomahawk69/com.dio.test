package com.dio.test.interfaces;

/**
 * Created by iovchynnikov on 4/16/14.
 * POJO formatter class
 */
public class ClassFormatter  {
    public static String commentText(String text, Language language) {
        return language.commentText(text);
    }
    public static String uncommentText(String text, Language language) {
        return language.uncommentText(text);
    }
}
