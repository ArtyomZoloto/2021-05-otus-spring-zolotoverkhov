package ru.otus.exceptions;

/**
 * General quiz exception
 */

public class QuizException extends RuntimeException {
    public QuizException(String message) {
        super(message);
    }
}