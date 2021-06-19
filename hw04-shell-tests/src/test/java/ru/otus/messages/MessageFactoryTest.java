package ru.otus.messages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.config.QuizConfig;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageFactoryTest {

    @InjectMocks
    MessageFactory messageFactory;
    @Mock
    private MessageSource messageSource;
    @Mock
    private QuizConfig quizConfig;

    @Test
    void getResultMessageNotPassEN() {
        QuizResult quizResult = new QuizResult(false, 50);
        User user = new User("Ivan", "Baranov");
        when(messageSource.getMessage("failed", null, Locale.forLanguageTag("en-EN"))).thenReturn("Test failed!");
        when(messageSource.getMessage("score", new String[]{user.toString(), "50"}, Locale.forLanguageTag("en-EN")))
                .thenReturn(user + " score is " + quizResult.getPercentage() + "%");
        when(quizConfig.getLocale()).thenReturn(Locale.forLanguageTag("en-EN"));
        quizResult.setUser(user);

        //act
        String result = messageFactory.getResultMessage(quizResult);

        //assert
        assertThat(result).isEqualTo("Ivan Baranov score is 50%" + System.lineSeparator() + "Test failed!");
    }

    @Test
    void getResultMessagePassEN() {
        QuizResult quizResult = new QuizResult(true, 50);
        User user = new User("Ivan", "Baranov");
        when(messageSource.getMessage("passed", null, Locale.forLanguageTag("en-EN"))).thenReturn("Test passed!");
        when(messageSource.getMessage("score", new String[]{user.toString(), "50"}, Locale.forLanguageTag("en-EN")))
                .thenReturn(user + " score is " + quizResult.getPercentage() + "%");
        when(quizConfig.getLocale()).thenReturn(Locale.forLanguageTag("en-EN"));
        quizResult.setUser(user);

        //act
        String result = messageFactory.getResultMessage(quizResult);

        //assert
        assertThat(result).isEqualTo("Ivan Baranov score is 50%" + System.lineSeparator() + "Test passed!");
    }

    @Test
    void getResultMessagePassRU() {
        QuizResult quizResult = new QuizResult(true, 50);
        User user = new User("Иван", "Баранов");
        when(messageSource.getMessage("passed", null, Locale.forLanguageTag("en-EN"))).thenReturn("Тест пройден!");
        when(messageSource.getMessage("score", new String[]{user.toString(), "50"}, Locale.forLanguageTag("en-EN")))
                .thenReturn(user + " набрал " + quizResult.getPercentage() + "%");
        when(quizConfig.getLocale()).thenReturn(Locale.forLanguageTag("en-EN"));
        quizResult.setUser(user);

        //act
        String result = messageFactory.getResultMessage(quizResult);

        //assert
        assertThat(result).isEqualTo("Иван Баранов набрал 50%" + System.lineSeparator() + "Тест пройден!");
    }

    @Test
    void getResultMessageFailRU() {
        QuizResult quizResult = new QuizResult(false, 50);
        User user = new User("Иван", "Баранов");
        when(messageSource.getMessage("failed", null, Locale.forLanguageTag("en-EN"))).thenReturn("Тест не пройден!");
        when(messageSource.getMessage("score", new String[]{user.toString(), "50"}, Locale.forLanguageTag("en-EN")))
                .thenReturn(user + " набрал " + quizResult.getPercentage() + "%");
        when(quizConfig.getLocale()).thenReturn(Locale.forLanguageTag("en-EN"));
        quizResult.setUser(user);

        //act
        String result = messageFactory.getResultMessage(quizResult);

        //assert
        assertThat(result).isEqualTo("Иван Баранов набрал 50%" + System.lineSeparator() + "Тест не пройден!");
    }

    @Test
    void getMessage() {
        Locale locale = Locale.forLanguageTag("en-EN");
        when(quizConfig.getLocale()).thenReturn(locale);

        //act
        messageFactory.getMessage(Messages.WELCOME);

        //assert
        verify(messageSource, Mockito.times(1)).getMessage("welcome", null, locale);
    }
}