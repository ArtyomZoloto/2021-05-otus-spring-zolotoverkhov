package ru.otus.domain;

public class QuizResult {
    private final boolean isPassed;
    private final int percentage;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
