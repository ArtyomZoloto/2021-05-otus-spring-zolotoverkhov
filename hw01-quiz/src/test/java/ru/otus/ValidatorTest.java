package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;


class ValidatorTest {

    @DisplayName("must be zero")
    @Test()
    void getScoreTest0Percent() {
        Validator validator = new Validator();
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(0, null, null)),
                new Answer(2, new Question(0, null, null))};
        long score = validator.getScore(answers);
        assertThat(score).isZero();
    }

    @DisplayName("must be 50%")
    @Test()
    void getScoreTest50Percent() {
        Validator validator = new Validator();
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(1, null, null)),
                new Answer(2, new Question(0, null, null))};
        long score = validator.getScore(answers);
        assertThat(score).isEqualTo(50);
    }

    @DisplayName("must be 100%")
    @Test()
    void getScoreTest100Percent() {
        Validator validator = new Validator();
        Answer[] answers = new Answer[]{
                new Answer(1, new Question(1, null, null)),
                new Answer(2, new Question(2, null, null))};
        long score = validator.getScore(answers);
        assertThat(score).isEqualTo(100);
    }
}