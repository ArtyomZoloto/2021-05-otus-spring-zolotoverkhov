package ru.otus.homework05jdbc.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.homework05jdbc.domain.Author;
import ru.otus.homework05jdbc.domain.Book;
import ru.otus.homework05jdbc.domain.Genre;


import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long bookId = rs.getLong("book_id");
        String bookTitle = rs.getString("book_title");

        Long authorId = rs.getLong("author_id");
        String authorName = rs.getString("author_name");

        Long genreId = rs.getLong("genre_id");
        String genreName = rs.getString("genre_name");

        return new Book(bookId, bookTitle,
                new Author(authorId, authorName),
                new Genre(genreId, genreName));
    }
}
