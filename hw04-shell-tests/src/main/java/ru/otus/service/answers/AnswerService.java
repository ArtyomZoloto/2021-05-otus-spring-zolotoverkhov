package ru.otus.service.answers;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

public interface AnswerService {

    /**
     * Produce user Answer for certain question
     *
     * @param question question, that will be stored in Answer object
     * @return complete immutable Answer
     */
    Answer getAnswerForQuestion(Question question);
}
