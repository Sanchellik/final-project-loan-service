package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.repository.TariffRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TariffRepositoryImpl implements TariffRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SELECT_ALL_QUERY = "SELECT * FROM tariff";
    private final String SELECT_EXISTS_ID_QUERY = "SELECT EXISTS(SELECT 1 FROM tariff WHERE id = :id)";
    private final String SELECT_TYPE_BY_ID_QUERY = "SELECT type FROM tariff WHERE id = :id";

    @Override
    public List<Tariff> getAllTariffs() {
        return namedParameterJdbcTemplate.query(
                SELECT_ALL_QUERY,
                new BeanPropertyRowMapper<>(Tariff.class));
    }

    @Override
    public boolean existsById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_EXISTS_ID_QUERY,
                parameters,
                Boolean.class
        );
    }

    @Override
    public String getTypeById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_TYPE_BY_ID_QUERY,
                parameters,
                String.class
        );
    }

}
