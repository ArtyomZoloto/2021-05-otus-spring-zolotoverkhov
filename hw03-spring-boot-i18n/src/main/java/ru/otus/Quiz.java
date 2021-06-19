package ru.otus;

import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;
import ru.otus.io.Printer;
import ru.otus.messages.MessageFactory;

@Component
public class Quiz {
    private final QuestionsProcessor processor;
    private final UserFactory userFactory;
    private final Printer printer;
    private final Validator validator;
    private final MessageFactory messageFactory;

    public Quiz(Printer printer,
                QuestionsProcessor processor,
                UserFactory userFactory,
                Validator validator,
                MessageFactory messageFactory) {
        this.printer = printer;
        this.processor = processor;
        this.userFactory = userFactory;
        this.validator = validator;
        this.messageFactory = messageFactory;
    }

    /**
     * Starts the quiz
     */
    public void start() {
        User user = userFactory.createUser();
        Answer[] answers = processor.processQuestions();
        QuizResult quizResult = validator.validate(answers);
        quizResult.setUser(user);
        printer.println(messageFactory.getResultMessage(quizResult));
    }
}
