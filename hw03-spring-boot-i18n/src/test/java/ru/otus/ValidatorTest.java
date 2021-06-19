package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.QuizResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    @Mock
    QuizConfig config;

    @BeforeEach
    void setUp() {
        when(config.getPassScore()).thenReturn(60);
    }

    @DisplayName("must be zero")
    @Test()
    void getScoreTest0Percent() {
        Validator validator = new Validator(config);
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(0, null, null)),
                new Answer(2, new Question(0, null, null))};
        QuizResult result = validator.validate(answers);
        assertThat(result.getPercentage()).isZero();
        assertThat(result.isPassed()).isFalse();
    }

    @DisplayName("must be 50% and fail")
    @Test()
    void getScoreTest50Percent() {
        Validator validator = new Validator(config);
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(1, null, null)),
                new Answer(2, new Question(0, null, null))};
        QuizResult result = validator.validate(answers);
        assertThat(result.getPercentage()).isEqualTo(50);
        assertThat(result.isPassed()).isFalse();
    }

    @DisplayName("must be 100% and pass")
    @Test()
    void getScoreTest100Percent() {
        Validator validator = new Validator(config);
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(1, null, null)),
                new Answer(2, new Question(2, null, null))};
        QuizResult result = validator.validate(answers);
        assertThat(result.getPercentage()).isEqualTo(100);
        assertThat(result.isPassed()).isTrue();
    }
}