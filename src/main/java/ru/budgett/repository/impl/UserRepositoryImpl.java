package ru.budgett.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.budgett.repository.api.UserRepository;
import ru.budgett.data.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate template;

    private static final String SQL_QUERY =
            "        SELECT id, name, birth_date, is_admin " +
                    "  FROM users ";
    private static final String SQL_USER_FILTER = "WHERE id = :ID";

    private static final String SQL_INSERT =
            "INSERT INTO users(name, birth_date, is_admin) " +
                    "VALUES ( :NAME, :BIRTH_DATE, :IS_ADMIN)";

    private static final String SQL_UPDATE =
            "        UPDATE users" +
                    "   SET name = :NAME, " +
                    "       birth_date = :BIRTH_DATE, " +
                    "       is_admin = :IS_ADMIN " +
                    " where id = :ID";

    private static final String SQL_DELETE =
            "        DELETE FROM users" +
                    " where id = :ID";


    @Override
    public void createUser(User user) {
        template.update(SQL_INSERT, getMapSqlParameterSource(user));
    }

    @Override
    public Optional<User> findUser(long userId) {
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource().addValue("ID", userId);

        return template.query(SQL_QUERY + SQL_USER_FILTER, sqlParameters, getResultDtoRowMapper())
                .stream()
                .findAny();
    }

    @Override
    public void deleteUser(long userId) {
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource().addValue("ID", userId);

        template.update(SQL_DELETE, sqlParameters);
    }

    @Override
    public void updateUser(User user) {
        template.update(SQL_UPDATE, getMapSqlParameterSource(user));
    }

    @Override
    public List<User> findAllUsers() {
        return template.query(SQL_QUERY, getResultDtoRowMapper());
    }

    private RowMapper<User> getResultDtoRowMapper() {
        return (resultSet, i) -> toDto(resultSet);
    }

    private User toDto(ResultSet resultSet) throws SQLException {
        return new User()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setBirthDate(resultSet.getTimestamp("birth_date").toLocalDateTime().toLocalDate())
                .setIsAdmin(resultSet.getBoolean("is_admin"))
                ;
    }

    private MapSqlParameterSource getMapSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("ID", user.getId())
                .addValue("NAME", user.getName())
                .addValue("BIRTH_DATE", user.getBirthDate())
                .addValue("IS_ADMIN", user.getIsAdmin())
                ;
    }
}
