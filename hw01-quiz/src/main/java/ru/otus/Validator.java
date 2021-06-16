package ru.otus;

import ru.otus.domain.Answer;

import java.util.stream.Stream;

/**
 * This class check, is user answer valid or not valid, and
 * return a percentage of right answers.
 */

public class Validator {

    /**
     * Map user answers with question data and make decision, is
     * question answer valid or not. After that return percentage
     * of right answers.
     * @param answers array of users answers
     * @return percentage of right answers
     */
    public long getScore(Answer[] answers) {
        long correctAnswersCounter =
                Stream.of(answers).filter(a -> a.getUserAnswer() == a.getQuestion().getCorrectAnswer()).count();
        return (long) ((double) correctAnswersCounter / answers.length * 100.0);
    }
}
