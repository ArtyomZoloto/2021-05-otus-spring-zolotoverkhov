package ru.otus.service.questions;

import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.IOService;
import ru.otus.service.answers.AnswerService;

import java.util.List;

/**
 * This class process questions one-by-one
 */

@Service
public class QuestionsServiceImpl implements QuestionsService{

    private final IOService ioService;
    private final AnswerService answerService;

    public QuestionsServiceImpl(IOService ioService,
                                AnswerService answerService) {
        this.ioService = ioService;
        this.answerService = answerService;
    }

    public Answer[] processQuestions(List<Question> questions) {
        Answer[] answers = new Answer[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            ioService.print(getQuestionWithOptionsString(questions.get(i)));
            answers[i] = answerService.getAnswerForQuestion(questions.get(i));
        }
        return answers;
    }

    /**
     * Text representation of Question with available options to answer
     *
     * @param question question object
     * @return formatted multi-line string
     */
    private String getQuestionWithOptionsString(Question question) {
        StringBuilder sb = new StringBuilder(question.getText());
        sb.append(System.lineSeparator());
        for (int i = 1; i <= question.getOptions().length; i++) {
            sb.append(i + ". " + question.getOptions()[i - 1]);
            if (i < question.getOptions().length) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
