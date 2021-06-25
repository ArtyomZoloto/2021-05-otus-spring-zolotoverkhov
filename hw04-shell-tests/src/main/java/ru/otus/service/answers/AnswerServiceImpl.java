package ru.otus.service.answers;

import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.IOService;
import ru.otus.messages.MessageFactory;

import static ru.otus.messages.MessagesConstants.REPEAT_INPUT;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final IOService ioService;
    private final MessageFactory messageFactory;

    public AnswerServiceImpl(IOService ioService,
                             MessageFactory messageFactory) {
        this.ioService = ioService;
        this.messageFactory = messageFactory;
    }

    public Answer getAnswerForQuestion(Question question) {
        return new Answer(getIntWithRepeatIfMismatch(), question);
    }

    private int getIntWithRepeatIfMismatch() {
        try {
            return Integer.parseInt(ioService.readString());
        } catch (NumberFormatException e) {
            ioService.print(messageFactory.getLocalizedMessage(REPEAT_INPUT));
            return this.getIntWithRepeatIfMismatch();
        }
    }
}
