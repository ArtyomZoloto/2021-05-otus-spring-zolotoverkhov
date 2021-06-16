package ru.otus.io;

import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * This printer used because we can't store
 * default IO classes in Spring Context
 */

@Component
public class Printer {
    private PrintStream ps;

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public void println(String s) {
        ps.println(s);
    }
}
