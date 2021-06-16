package ru.otus.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserInputTest {

    @Test
    void nextIntShouldRepeat() {
        //arrange
        String input = "abc" + System.lineSeparator() + "1";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Printer printerMock = mock(Printer.class);
        UserInput userInput = new UserInput(printerMock);
        userInput.setIn(is);

        //act
        int result = userInput.nextInt();

        //assert
        verify(printerMock).println("please use proper value!");
        assertThat(result).isOne();
    }

    @Test
    void nextString() {
        //arrange
        String input = "abc";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        UserInput userInput = new UserInput(null);
        userInput.setIn(is);

        //act
        String result = userInput.nextString();

        //assert
        assertThat(result).isEqualTo("abc");
    }
}