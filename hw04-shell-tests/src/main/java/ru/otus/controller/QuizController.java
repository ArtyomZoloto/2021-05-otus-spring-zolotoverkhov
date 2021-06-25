package ru.otus.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.quiz.QuizService;

/**
 * This class allow user interaction based on commands
 */

@ShellComponent
public class QuizController{

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login(String name, String surname) {
        quizService.login(name, surname);
    }

    @ShellMethod(value = "Who a me", key = {"whoami", "whoame"})
    public void whoami() {
        quizService.whoami();
    }

    @ShellMethod(value = "Starts quiz", key = "start")
    public void startQuiz() {
        quizService.startQuiz();
    }

    @ShellMethod(value = "Show results", key = "result")
    public void showResults() {
        quizService.getResult();
    }

    @ShellMethod(value = "Reset quiz data", key = "reset")
    public void restart() {
        quizService.reset();
    }

    @ShellMethod(value = "LogOut", key = "logout")
    public void logout() {
        quizService.logout();
    }
}
