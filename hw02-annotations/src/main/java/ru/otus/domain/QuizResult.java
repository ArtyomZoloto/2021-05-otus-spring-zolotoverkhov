package ru.otus.domain;

public class QuizResult {
    private boolean isPassed;
    private int percentage;

    public QuizResult(boolean isPassed, int percentage) {
        this.isPassed = isPassed;
        this.percentage = percentage;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public int getPercentage() {
        return percentage;
    }
}
