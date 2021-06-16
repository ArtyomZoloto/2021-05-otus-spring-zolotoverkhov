package ru.otus;

import ru.otus.domain.User;
import ru.otus.io.Printer;
import ru.otus.io.UserInput;

/**
 * This class produce User for the quiz
 */

public class UserFactory {
    private final Printer printer;
    private final UserInput input;

    public UserFactory(Printer printer, UserInput input) {
        this.printer = printer;
        this.input = input;
    }

    /**
     * Asks user for name and surname, produce User object
     * @return User object
     */
    public User createUser() {
        printer.println("Welcome! Enter you name and surname:");
        printer.println("Name:");
        String name = input.nextString();
        printer.println("Surname:");
        String surname = input.nextString();
        return new User(name, surname);
    }
}
