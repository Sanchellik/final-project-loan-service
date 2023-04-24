package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Tariff;
import ru.gozhan.loanservice.repository.TariffRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TariffRepositoryImpl implements TariffRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SELECT_ALL_QUERY = "SELECT * FROM tariff";

    @Override
    public List<Tariff> getAllTariffs() {
        return namedParameterJdbcTemplate.query(
                SELECT_ALL_QUERY,
                new BeanPropertyRowMapper<>(Tariff.class));
    }

}
