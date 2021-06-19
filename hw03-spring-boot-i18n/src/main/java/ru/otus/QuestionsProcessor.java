package ru.otus;

import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.Printer;

import java.util.List;

/**
 * This class process questions one-by-one
 */

@Service
public class QuestionsProcessor {

    private final Printer printer;
    private final QuestionsDao dao;
    private final AnswerService answerService;

    public QuestionsProcessor(Printer printer,
                              QuestionsDao dao,
                              AnswerService answerService) {
        this.printer = printer;
        this.dao = dao;
        this.answerService = answerService;
    }

    /**
     * gets questions from DAO and map questions
     * with corresponding user answers
     *
     * @return array of user answers
     */
    public Answer[] processQuestions() {
        List<Question> questions = dao.getAll();
        Answer[] answers = new Answer[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            printer.println(questions.get(i).toString());
            answers[i] = answerService.getFor(questions.get(i));
        }
        return answers;
    }
}
