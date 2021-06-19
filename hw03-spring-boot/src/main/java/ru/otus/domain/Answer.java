package ru.otus.domain;

/**
 * Answer for quiz. Immutable.
 */

public class Answer {
    private final int userAnswer;
    private final Question question;

    public Answer(int userAnswer, Question question) {
        this.userAnswer = userAnswer;
        this.question = question;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public Question getQuestion() {
        return question;
    }
}
