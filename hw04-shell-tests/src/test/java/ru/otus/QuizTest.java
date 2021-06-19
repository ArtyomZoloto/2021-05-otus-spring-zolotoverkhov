package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;
import ru.otus.io.Printer;
import ru.otus.messages.MessageFactory;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizTest {

    @Mock
    QuestionsProcessor processor;

    @Mock
    UserFactory userFactory;

    @Mock
    Printer printer;

    @Mock
    Validator validator;

    @Mock
    MessageFactory messageFactory;

    @InjectMocks
    Quiz quiz;

    private Answer[] answers;

    @BeforeEach
    void setUp() {
        when(userFactory.createUser()).thenReturn(new User("Ivan", "Ivanov"));
        Question question1 = new Question(1, "text",
                new String[]{"one", "two", "three", "four"});
        Question question2 = new Question(2, "text2",
                new String[]{"one", "two", "three", "four"});
        answers = new Answer[]{
                new Answer(1, question1),
                new Answer(2, question2)
        };
        when(processor.processQuestions()).thenReturn(answers);

    }

    @Test
    void startPassed() {
        when(messageFactory.getResultMessage(any(QuizResult.class))).thenReturn("passed-message");
        when(validator.validate(any())).thenReturn(new QuizResult(true, 75));

        //act
        quiz.start();

        //assert
        verify(userFactory, times(1)).createUser();
        verify(processor, times(1)).processQuestions();
        verify(validator, times(1)).validate(answers);
        verify(printer, times(1)).println("passed-message");
    }

    @Test
    void startFailed() {
        when(messageFactory.getResultMessage(any(QuizResult.class))).thenReturn("failed-message");

        when(validator.validate(any())).thenReturn(new QuizResult(false, 30));

        //act
        quiz.start();

        //assert
        verify(userFactory, times(1)).createUser();
        verify(processor, times(1)).processQuestions();
        verify(validator, times(1)).validate(answers);
        verify(printer, times(1)).println("failed-message");
    }
}