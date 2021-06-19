package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionsDaoImplTest {

    @Mock
    private QuizConfig config;

    @InjectMocks
    private QuestionsDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new QuestionsDaoImpl(config);
    }

    @Test
    void getAll() {
        when(config.getLocale()).thenReturn(Locale.forLanguageTag("en-EN"));
        when(config.getFileName()).thenReturn("questions-test.csv");
        List<Question> questionList = dao.getAll();
        assertThat(questionList).hasSize(2);

        assertThat(questionList.get(0).getText()).isEqualTo("text1");
        assertThat(questionList.get(0).getCorrectAnswer()).isOne();
        assertThat(questionList.get(0).getOptions()[0]).isEqualTo("a");
        assertThat(questionList.get(0).getOptions()[1]).isEqualTo("b");
        assertThat(questionList.get(0).getOptions()[2]).isEqualTo("c");
        assertThat(questionList.get(0).getOptions()[3]).isEqualTo("d");

        assertThat(questionList.get(1).getText()).isEqualTo("text2");
        assertThat(questionList.get(1).getCorrectAnswer()).isEqualTo(2);
        assertThat(questionList.get(1).getOptions()[0]).isEqualTo("e");
        assertThat(questionList.get(1).getOptions()[1]).isEqualTo("f");
        assertThat(questionList.get(1).getOptions()[2]).isEqualTo("g");
        assertThat(questionList.get(1).getOptions()[3]).isEqualTo("h");
    }

    @DisplayName("should drop error")
    @Test
    void getAllWithError() {
        assertThatThrownBy(() -> {
            dao.getAll();
        }).isInstanceOf(NullPointerException.class);
    }

}