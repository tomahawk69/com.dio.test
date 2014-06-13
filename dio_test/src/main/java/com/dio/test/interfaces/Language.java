package com.dio.test.interfaces;

/**
 * Created by iovchynnikov on 4/16/14.
 * Language interface
 */
public interface Language {
    String asString();
    String commentText(String text);
    String uncommentText(String text);
    Boolean isCommented(String text);
}
