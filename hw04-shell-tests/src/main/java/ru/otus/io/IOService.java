package ru.otus.io;

/**
 * Main IO service for the Quiz application
 */

public interface IOService {

    /**
     * send string to the output
     * @param str string to print
     */
    void print(String str);

    /**
     * Require user input string
     * @return String, provided by user
     */
    String readString();
}
