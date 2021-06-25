package ru.otus.homework05jdbc.dao.book;

import ru.otus.homework05jdbc.domain.Book;

import java.util.Optional;

/**
 * DAO for Book, supports CRUD operations.
 */

public interface BookDao {

    Optional<Book> findByTitleAndAuthorAndGenre(String title, String author, String genre);

    Optional<Book> findById(Long id);

    Long insert(Book book);

    boolean update(Book book);

    boolean deleteById(Long id);
}
