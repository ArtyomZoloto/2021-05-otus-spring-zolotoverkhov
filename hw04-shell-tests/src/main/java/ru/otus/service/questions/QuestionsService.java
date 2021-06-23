package ru.otus.service.questions;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;

public interface QuestionsService {

    /**
     * gets questions from DAO and map questions
     * with corresponding user answers
     *
     * @return array of user answers
     */
    Answer[] processQuestions(List<Question> questions);
}
