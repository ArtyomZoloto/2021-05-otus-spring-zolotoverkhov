package ru.otus.messages;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.config.QuizConfig;

@Component
public class MessageFactory {

    private final MessageSource messageSource;
    private final QuizConfig config;

    public MessageFactory(MessageSource messageSource, QuizConfig config) {
        this.messageSource = messageSource;
        this.config = config;
    }

    public String getLocalizedMessage(String msg, Object... args) {
        return messageSource.getMessage(msg, args,
                config.getLocale());
    }
}
