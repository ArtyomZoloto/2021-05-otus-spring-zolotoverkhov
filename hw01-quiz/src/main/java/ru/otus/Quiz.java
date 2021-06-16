package ru.otus;

import ru.otus.domain.Answer;
import ru.otus.domain.User;
import ru.otus.io.Printer;

public class Quiz {
    private final QuestionsProcessor processor;
    private final UserFactory userFactory;
    private final Printer printer;
    private final Validator validator;


    public Quiz(Printer printer,
                QuestionsProcessor processor,
                UserFactory userFactory,
                Validator validator) {
        this.printer = printer;
        this.processor = processor;
        this.userFactory = userFactory;
        this.validator = validator;
    }

    /**
     * Starts the quiz
     */
    public void start() {
        User user = userFactory.createUser();
        Answer[] answers = processor.processQuestions();
        long score = validator.getScore(answers);
        printer.println(user + " score is " + score + "%");
    }
}
