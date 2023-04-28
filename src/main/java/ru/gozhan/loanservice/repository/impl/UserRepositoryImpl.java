package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.User;
import ru.gozhan.loanservice.repository.UserRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String DEF_FIND_USER_BY_USERNAME_SQL = "SELECT * FROM usr WHERE username = :username";

    @Override
    public Optional<User> findByUsername(String username) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", username);

        User user = namedParameterJdbcTemplate.queryForObject(
                DEF_FIND_USER_BY_USERNAME_SQL,
                parameters,
                new BeanPropertyRowMapper<>(User.class)
        );
        return user == null ? Optional.empty() : Optional.of(user);
    }

}
