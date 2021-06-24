package ru.otus.homework05jdbc.dao.author;

import ru.otus.homework05jdbc.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findByName(String name);

    Long insert(Author book);

}
