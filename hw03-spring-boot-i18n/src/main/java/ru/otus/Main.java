package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.io.Printer;
import ru.otus.io.UserInput;

/**
 * Main class for Quiz.
 * Used setter for Spring context - sensitive IO objects.
 */


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Printer printer = (Printer) context.getBean("printer");
        printer.setPs(System.out);
        UserInput input = (UserInput) context.getBean("userInput");
        input.setIn(System.in);
        Quiz quiz = (Quiz) context.getBean("quiz");
        quiz.start();
    }
}
