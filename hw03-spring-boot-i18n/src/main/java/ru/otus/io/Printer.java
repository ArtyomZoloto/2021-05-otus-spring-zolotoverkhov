package ru.otus.io;

import org.springframework.stereotype.Component;
import ru.otus.messages.MessageFactory;

import java.io.PrintStream;

/**
 * This printer used because we can't store
 * default IO classes in Spring Context
 */

@Component
public class Printer {

    MessageFactory messageFactory;
    private PrintStream ps;

    public Printer(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public void println(String s) {
        ps.println(s);
    }
}
