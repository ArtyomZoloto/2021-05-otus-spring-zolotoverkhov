package ru.otus.io;

import java.io.PrintStream;

/**
 * This printer used because we can't store
 * default IO classes in Spring Context
 */

public class Printer {
    private PrintStream ps;

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public void println(String s) {
        ps.println(s);
    }
}
