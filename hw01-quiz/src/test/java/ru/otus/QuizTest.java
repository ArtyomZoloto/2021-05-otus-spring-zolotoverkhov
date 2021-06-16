package ru.otus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.io.Printer;

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

    @InjectMocks
    Quiz quiz;

    @Test
    void start() {
        when(userFactory.createUser()).thenReturn(new User("Ivan", "Ivanov"));
        Question question1 = new Question(1, "text",
                new String[]{"one", "two", "three", "four"});
        Question question2 = new Question(2, "text2",
                new String[]{"one", "two", "three", "four"});
        Answer[] answers = new Answer[]{
                new Answer(1, question1),
                new Answer(2, question2)
        };
        when(processor.processQuestions()).thenReturn(answers);
        when(validator.getScore(any())).thenReturn(75L);

        //act
        quiz.start();

        //assert
        verify(userFactory, times(1)).createUser();
        verify(processor, times(1)).processQuestions();
        verify(validator, times(1)).getScore(answers);
        verify(printer, times(1)).println("Ivan Ivanov score is 75%");
    }
}