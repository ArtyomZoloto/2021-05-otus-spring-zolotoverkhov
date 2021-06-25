package ru.otus.domain;

/**
 * Question for Quiz. Immutable.
 */

public class Question {
    private final int correctAnswer;
    private final String text;
    private final String[] options;

    public Question(int correctAnswer, String text, String[] options) {
        this.correctAnswer = correctAnswer;
        this.text = text;
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }



}

