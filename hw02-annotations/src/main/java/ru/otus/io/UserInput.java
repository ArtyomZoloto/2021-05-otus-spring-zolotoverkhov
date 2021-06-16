package ru.otus.io;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This input used because we can't store
 * default IO classes in Spring Context
 */

@Component
public class UserInput {
    private Scanner scanner;
    private final Printer printer;

    public UserInput(Printer printer) {
        this.printer = printer;
    }

    public void setIn(InputStream is) {
        this.scanner = new Scanner(is);
    }

    /**
     * Get int from scanner and re-run if input mismatch.
     * @return int value
     */
    public int nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            printer.println("please use proper value!");
            return this.nextInt();
        }
    }

    public String nextString() {
        return scanner.nextLine();
    }
}
