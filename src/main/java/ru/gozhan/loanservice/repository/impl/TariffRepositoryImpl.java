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

    private final String DEF_FIND_TARIFFS_SQL = "SELECT * FROM tariff";

    @Override
    public List<Tariff> getAllTariffs() {
        return namedParameterJdbcTemplate.query(
                DEF_FIND_TARIFFS_SQL,
                new BeanPropertyRowMapper<>(Tariff.class));
    }

}
