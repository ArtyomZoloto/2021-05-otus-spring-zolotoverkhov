package ru.otus.homework05jdbc;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework05JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework05JdbcApplication.class, args);
        try {
            Console.main(args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
