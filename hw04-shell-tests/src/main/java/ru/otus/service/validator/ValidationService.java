package ru.otus.service.validator;

import ru.otus.domain.Answer;
import ru.otus.domain.QuizResult;

public interface ValidationService {

    /**
     * Map user answers with question data and make decision, is
     * question answer valid or not. After that return percentage
     * of right answers.
     *
     * @param answers array of users answers
     * @return percentage of right answers
     */
    QuizResult getQuizResult(Answer[] answers);
}
