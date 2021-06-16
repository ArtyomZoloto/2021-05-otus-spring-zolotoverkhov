package ru.otus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.io.Printer;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionsProcessorTest {

    @Mock
    Printer printerMock;

    @Mock
    QuestionsDao daoMock;

    @Mock
    AnswerService answerServiceMock;

    @InjectMocks
    QuestionsProcessor processor;

    @Test
    void processMultipleQuestionsTest() {
        Question question = new Question(1, "text",
                new String[]{"one", "two", "three", "four"});
        Question question2 = new Question(2, "text2",
                new String[]{"one", "two", "three", "four"});
        when(daoMock.getAll()).thenReturn(Arrays.asList(question, question2));
        when(answerServiceMock.getFor(question)).thenReturn(new Answer(1, question));
        when(answerServiceMock.getFor(question2)).thenReturn(new Answer(2, question2));

        //act
        Answer[] answers = processor.processQuestions();

        //assert
        assertThat(answers.length).isEqualTo(2);
        assertThat(answers[0].getUserAnswer()).isEqualTo(1);
        assertThat(answers[0].getQuestion()).isEqualTo(question);

        assertThat(answers[1].getUserAnswer()).isEqualTo(2);
        assertThat(answers[1].getQuestion()).isEqualTo(question2);

        verify(printerMock, times(1)).println(question.toString());
        verify(printerMock, times(1)).println(question2.toString());
    }
}