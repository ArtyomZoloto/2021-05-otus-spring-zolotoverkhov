package ru.otus.homework05jdbc.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Author author;
    private Genre genre;

    @Override
    public String toString() {
        return author.getName() + " " + title + " " + genre.getName() + ", id=" +id;
    }
}
