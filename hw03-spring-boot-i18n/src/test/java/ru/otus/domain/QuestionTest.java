package ru.otus.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    void testToString() {
        Question question = new Question(1, "text", new String[]{
                "one", "two", "three", "four"});
        String toString = question.toString();
        assertThat(toString).isEqualTo("text"
                + System.lineSeparator()
                + "1. one"
                + System.lineSeparator()
                + "2. two"
                + System.lineSeparator()
                + "3. three"
                + System.lineSeparator()
                + "4. four"
        );
    }
}