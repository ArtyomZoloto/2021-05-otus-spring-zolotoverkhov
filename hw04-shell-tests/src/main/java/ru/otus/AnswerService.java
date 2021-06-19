package ru.otus;

import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.UserInput;

@Service
public class AnswerService {

    private final UserInput input;

    public AnswerService(UserInput input) {
        this.input = input;
    }

    /**
     * Produce user Answer for certain question
     *
     * @param question question, that will be stored in Answer object
     * @return complete immutable Answer
     */
    public Answer getFor(Question question) {
        return new Answer(input.nextInt(), question);
    }
}
