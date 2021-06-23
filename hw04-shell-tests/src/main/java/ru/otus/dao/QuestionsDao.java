package ru.otus.dao;

import ru.otus.domain.Question;

import java.util.List;

/**
 * Data access object to read questions data
 */

public interface QuestionsDao {

    /**
     * Return list of Questions object for quiz
     */
    List<Question> getAll();
}
