package ru.otus.homework05jdbc.dao.genre;

import ru.otus.homework05jdbc.domain.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findByName(String name);

    Long insert(Genre genre);

}
