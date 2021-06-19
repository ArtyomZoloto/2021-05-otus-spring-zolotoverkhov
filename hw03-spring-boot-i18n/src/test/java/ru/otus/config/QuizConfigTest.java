package ru.otus.config;

import org.junit.jupiter.api.Test;
import ru.otus.exceptions.QuizException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizConfigTest {

    @Test
    void validateFileNameisOK() {
        QuizConfig quizConfig = new QuizConfig();
        setAllFields(quizConfig);
        quizConfig.validate();
        assertDoesNotThrow(() -> quizConfig.validate());

    }


    @Test
    void validateFileNameFailedEmpty() {
        QuizConfig quizConfig = new QuizConfig();
        quizConfig.setFileName("");
        assertThrows(QuizException.class, () -> quizConfig.validate());
    }

    @Test
    void validateFileNameFailedNull() {
        QuizConfig quizConfig = new QuizConfig();
        quizConfig.setFileName(null);
        assertThrows(QuizException.class, () -> quizConfig.validate());
    }

    @Test
    void validatePassScoreIsNull() {
        QuizConfig quizConfig = new QuizConfig();
        quizConfig.setPassScore(null);
        assertThrows(QuizException.class, () -> quizConfig.validate());
    }

    @Test
    void validatePassScoreIsLessThanZero() {
        QuizConfig quizConfig = new QuizConfig();
        quizConfig.setPassScore(-100);
        assertThrows(QuizException.class, () -> quizConfig.validate());
    }

    @Test
    void validatePassScoreIsGreaterThan100() {
        QuizConfig quizConfig = new QuizConfig();
        quizConfig.setPassScore(101);
        assertThrows(QuizException.class, () -> quizConfig.validate());
    }

    private void setAllFields(QuizConfig config) {
        config.setPassScore(100);
        config.setFileName("filename");

    }
}