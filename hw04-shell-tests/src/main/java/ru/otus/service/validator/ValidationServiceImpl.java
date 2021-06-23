package ru.otus.service.validator;

import org.springframework.stereotype.Component;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.QuizResult;

import java.util.stream.Stream;

/**
 * This class check, is user answer valid or not valid, and
 * return a percentage of right answers.
 */

@Component
public class ValidationServiceImpl implements ValidationService{
    private final QuizConfig config;

    public ValidationServiceImpl(QuizConfig config) {
        this.config = config;
    }

    public QuizResult getQuizResult(Answer[] answers) {

        long correctAnswersCounter =
                Stream.of(answers).filter(a -> a.getUserAnswer() == a.getQuestion().getCorrectAnswer()).count();
        int userPercentage = (int) ((double) correctAnswersCounter / answers.length * 100.0);
        boolean isPassed = userPercentage >= config.getPassScore();
        return new QuizResult(isPassed, userPercentage);
    }
}
