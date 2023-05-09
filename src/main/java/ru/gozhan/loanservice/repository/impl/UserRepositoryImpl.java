package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.repository.UserRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<Long> getUserIdByUsername(String username) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", username);

        String SELECT_USER_ID_BY_USERNAME_QUERY = "SELECT id FROM usr WHERE username = :username";

        Long userId = namedParameterJdbcTemplate.queryForObject(
                SELECT_USER_ID_BY_USERNAME_QUERY,
                parameters,
                Long.class
        );
        return Optional.ofNullable(userId);
    }

}
