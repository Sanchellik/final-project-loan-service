package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SELECT_USER_ID_BY_USERNAME_QUERY = "SELECT id FROM usr WHERE username = :username";

    @Override
    public Long getUserIdByUsername(String username) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", username);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_USER_ID_BY_USERNAME_QUERY,
                parameters,
                Long.class
        );
    }

}
