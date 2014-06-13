package com.dio.test.interfaces;

/**
 * Created by iovchynnikov on 4/16/14.
 * abstract language class
 */
abstract public class ClassLanguage implements Language {
    protected String stringSymbol;
    @Override
    public String asString() {
        return "AbstractClass";
    }

    @Override
    abstract public Boolean isCommented(String text);
    @Override
    abstract public String commentText(String text);

    private static String testString1 = "One line sentence";
    private static String testString2 = "Two lines sentence\nThis is second line";
    public static void main(String[] args) {
        Language[] languages = {
            new ClassLanguageJava(),
        new ClassLanguageSQL()
        };

        for (Language language: languages) {
            System.out.println(language);
            System.out.println("First comment:\n" + ClassFormatter.commentText(testString1, language));
            System.out.println("Second comment:\n" + ClassFormatter.commentText(testString2, language));
            System.out.println("Embedded comment:\n" + ClassFormatter.commentText(ClassFormatter.commentText(testString1, language), language));
            System.out.println("First uncomment:\n" + ClassFormatter.uncommentText(ClassFormatter.commentText(testString1, language), language));
            System.out.println("Second uncomment:\n" + ClassFormatter.uncommentText(ClassFormatter.commentText(testString2, language), language));
        }

    }
}
