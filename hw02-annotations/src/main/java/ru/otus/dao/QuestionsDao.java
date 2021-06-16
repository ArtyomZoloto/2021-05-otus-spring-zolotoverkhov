package ru.otus.dao;

import ru.otus.domain.Question;

import java.util.List;

/**
 * Return list of Questions object for quiz
 */

public interface QuestionsDao {
    List<Question> getAll();
}
