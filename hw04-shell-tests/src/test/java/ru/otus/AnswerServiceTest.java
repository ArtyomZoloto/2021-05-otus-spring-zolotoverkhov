package ru.otus;

import org.junit.jupiter.api.Test;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.UserInput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AnswerServiceTest {

    @Test
    void getFor() {
        //arrange
        UserInput inputMock = mock(UserInput.class);
        when(inputMock.nextInt()).thenReturn(1);
        AnswerService service = new AnswerService(inputMock);
        Question question = new Question(1, null, null);

        //act
        Answer answer = service.getFor(question);

        //assert
        assertThat(answer.getUserAnswer()).isOne();
        assertThat(answer.getQuestion()).isEqualTo(question);
    }
}