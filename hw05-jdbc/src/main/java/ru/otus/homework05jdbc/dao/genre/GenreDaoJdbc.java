package ru.otus.homework05jdbc.dao.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Optional<Genre> findByName(String name) {
        return jdbcOperations.queryForStream("select id, name from GENRES where name=:name",
                Map.of("name", name), new GenreRowMapper()).findFirst();
    }

    @Override
    public Long insert(Genre genre) {
        Map<String, Object> params = Map.of(
                "name", genre.getName()
        );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(
                "insert into GENRES(name) values(:name)",
                new MapSqlParameterSource(params),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }

}
