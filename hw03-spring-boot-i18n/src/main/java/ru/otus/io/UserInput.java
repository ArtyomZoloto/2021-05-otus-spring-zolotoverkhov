package ru.otus.io;

import org.springframework.stereotype.Component;
import ru.otus.messages.MessageFactory;
import ru.otus.messages.Messages;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This input used because we can't store
 * default IO classes in Spring Context
 */

@Component
public class UserInput {
    private final Printer printer;
    private final MessageFactory messageFactory;
    private Scanner scanner;

    public UserInput(Printer printer, MessageFactory messageFactory) {
        this.printer = printer;
        this.messageFactory = messageFactory;
    }

    public void setIn(InputStream is) {
        this.scanner = new Scanner(is);
    }

    /**
     * Get int from scanner and re-run if input mismatch.
     *
     * @return int value
     */
    public int nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            printer.println(messageFactory.getMessage(Messages.REPEAT_INPUT));
            return this.nextInt();
        }
    }

    public String nextString() {
        return scanner.nextLine();
    }
}
