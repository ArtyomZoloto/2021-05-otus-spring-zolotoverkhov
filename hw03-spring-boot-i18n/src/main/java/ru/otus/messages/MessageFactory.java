package ru.otus.messages;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.config.QuizConfig;
import ru.otus.domain.QuizResult;
import static ru.otus.messages.Messages.*;

@Component
public class MessageFactory {

    private final MessageSource messageSource;
    private final QuizConfig config;

    public MessageFactory(MessageSource messageSource, QuizConfig config) {
        this.messageSource = messageSource;
        this.config = config;
    }

    public String getResultMessage(QuizResult result) {
        String score =
                messageSource.getMessage(SCORE.name().toLowerCase(), new String[]{result.getUser().toString(),
                                String.valueOf(result.getPercentage())},
                        config.getLocale());
        String passResult;

        if (result.isPassed()) {
            passResult = messageSource.getMessage(PASSED.name().toLowerCase(), null,
                    config.getLocale());
        } else {
            passResult = messageSource.getMessage(FAILED.name().toLowerCase(), null,
                    config.getLocale());
        }

        return score + System.lineSeparator() + passResult;
    }

    public String getMessage(Messages msg) {
        return messageSource.getMessage(msg.name().toLowerCase(), null,
                config.getLocale());
    }
}
