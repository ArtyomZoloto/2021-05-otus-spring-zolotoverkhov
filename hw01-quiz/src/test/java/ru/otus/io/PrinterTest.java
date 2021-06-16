package ru.otus.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class PrinterTest {

    @Test
    void println() {
        //arrange
        Printer printer = new Printer();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        printer.setPs(ps);

        //act
        printer.println("abc");

        //assert
        assertThat(baos.toString()).isEqualTo("abc" + System.lineSeparator());
    }
}