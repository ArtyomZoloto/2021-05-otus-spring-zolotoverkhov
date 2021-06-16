package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.domain.Question;
import ru.otus.exceptions.QuizException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionsDaoImplTest {

    @Test
    void getAll() {
        QuestionsDao questionsDao = new QuestionsDaoImpl("/questions-test.csv");
        List<Question> questionList = questionsDao.getAll();
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
    void getAllWithError(){
        assertThatThrownBy(()->{
            new QuestionsDaoImpl("no-file").getAll();
        }).isInstanceOf(NullPointerException.class);
    }

}