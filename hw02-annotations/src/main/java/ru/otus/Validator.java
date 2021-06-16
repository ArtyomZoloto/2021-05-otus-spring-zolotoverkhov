package ru.otus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.QuizResult;

import java.util.stream.Stream;

/**
 * This class check, is user answer valid or not valid, and
 * return a percentage of right answers.
 */

@Component
public class Validator {

    private int passThreshold = 0;

    public Validator(@Value("${quiz.pass.threshold.percentage}") int passThreshold) {
        this.passThreshold = passThreshold;
    }

    /**
     * Map user answers with question data and make decision, is
     * question answer valid or not. After that return percentage
     * of right answers.
     * @param answers array of users answers
     * @return percentage of right answers
     */
    public QuizResult validate(Answer[] answers) {
        long correctAnswersCounter =
                Stream.of(answers).filter(a -> a.getUserAnswer() == a.getQuestion().getCorrectAnswer()).count();
         int userPercentage = (int) ((double) correctAnswersCounter / answers.length * 100.0);
         boolean isPassed = userPercentage >= passThreshold;
         return new QuizResult(isPassed, userPercentage);
    }
}
