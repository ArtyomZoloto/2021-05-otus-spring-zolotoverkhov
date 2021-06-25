package ru.otus.homework05jdbc.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05jdbc.domain.Book;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {

    private final BookMapper bookMapper;
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Optional<Book> findByTitleAndAuthorAndGenre(String title,
                                                       String author,
                                                       String genre) {

        return jdbcOperations.queryForStream(
                "select b.id as book_id," +
                        " b.title as book_title, " +
                        "a.id as author_id, " +
                        "a.name as author_name," +
                        " g.id as genre_id, " +
                        "g.name as genre_name " +
                        "from BOOKS b " +
                        "join AUTHORS a on b.author_id=a.id " +
                        "join GENRES g on b.genre_id=g.id " +
                        "where b.title=:title " +
                        "AND a.name=:author " +
                        "AND g.name=:genre",
                Map.of("title", title, "author", author, "genre", genre),
                bookMapper)
                .findFirst();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jdbcOperations.queryForStream(
                "select b.id as book_id," +
                        " b.title as book_title, " +
                        "a.id as author_id, " +
                        "a.name as author_name," +
                        " g.id as genre_id, " +
                        "g.name as genre_name " +
                        "from BOOKS b " +
                        "join AUTHORS a on b.author_id=a.id " +
                        "join GENRES g on b.genre_id=g.id " +
                        "where b.id=:id", Map.of("id", id), bookMapper)
                .findFirst();
    }

    @Override
    public Long insert(Book book) {
        Map<String, Object> params = Map.of(
                "title", book.getTitle(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId()
        );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(
                "insert into BOOKS(title, author_id, genre_id)" +
                        " values(:title, :author_id, :genre_id)",
                new MapSqlParameterSource(params),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public boolean update(Book book) {
        return 1 == jdbcOperations.update(
                "update BOOKS " +
                        "set title=:title, " +
                        "author_id=:author_id, " +
                        "genre_id=:genre_id " +
                        "where id=:id",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
    }

    @Override
    public boolean deleteById(Long id) {
        return 1 == jdbcOperations.update(
                "delete from BOOKS where id=:id", Map.of("id", id));
    }
}
