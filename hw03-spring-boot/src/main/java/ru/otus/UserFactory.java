package ru.otus;

import org.springframework.stereotype.Component;
import ru.otus.domain.User;
import ru.otus.io.Printer;
import ru.otus.io.UserInput;
import ru.otus.messages.MessageFactory;
import ru.otus.messages.Messages;

/**
 * This class produce User for the quiz
 */

@Component
public class UserFactory {
    private final Printer printer;
    private final UserInput input;
    private final MessageFactory messageFactory;

    public UserFactory(Printer printer, UserInput input, MessageFactory messageFactory) {
        this.printer = printer;
        this.input = input;
        this.messageFactory = messageFactory;
    }

    /**
     * Asks user for name and surname, produce User object
     *
     * @return User object
     */
    public User createUser() {
        printer.println(messageFactory.getMessage(Messages.WELCOME));
        printer.println(messageFactory.getMessage(Messages.NAME));
        String name = input.nextString();
        printer.println(messageFactory.getMessage(Messages.SURNAME));
        String surname = input.nextString();
        return new User(name, surname);
    }
}
