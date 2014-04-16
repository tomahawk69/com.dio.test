package com.dio.test.interfaces;

/**
 * Created by iovchynnikov on 4/16/14.
 */
public class ClassLanguageJava extends ClassLanguage {
    private static String COMMENT_STRING = "//";
    private static String REGEX_COMMENT_STRING = "//";

    public String toString() {
        return "Language Java";
    }

    @Override
    public String uncommentText(String text) {
        String[] lines = text.split("\n");
        text = "";
        for (int i = 0; i < lines.length; i++) {
            lines[i] = uncommentLine(lines[i]);
            text += (i == 0 ? "" : "\n") + lines[i];
        }
        return text;
    }

    private String commentLine(String line) {
        if (!isCommented(line))
            return COMMENT_STRING + line;
        else
            return line;
    }

    /**
     * Function that uncommented text
     * @param line input string
     * @return uncommented string
     */
    private String uncommentLine(String line) {
        if (isCommented(line))
            return line.replaceFirst("\\s*" + REGEX_COMMENT_STRING, "");
        else
            return line;
    }

    /**
     * Method generally split string on lines and add comment symbols to every of lines
     * @param text input string
     * @return text
     */
    @Override
    public String commentText(String text) {
        String[] lines = text.split("\n");
        text = "";
        for (int i = 0; i < lines.length; i++) {
            lines[i] = commentLine(lines[i]);
            text += (i == 0 ? "" : "\n") + lines[i];
        }
        return text;
    }

    @Override
    public Boolean isCommented(String text) {
        return text.matches("\\s*" + REGEX_COMMENT_STRING + ".*");
    }
}
