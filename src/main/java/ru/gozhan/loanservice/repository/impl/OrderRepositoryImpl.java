package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String INSERT_QUERY = "INSERT INTO loan_order (order_id, user_id, tariff_id, credit_rating, status, " +
            "time_insert, time_update) VALUES (:orderId, :userId, :tariffId, :creditRating, :status, :timeInsert, :timeUpdate)";

    private final String SELECT_ORDERS_BY_USERID_QUERY = "SELECT * FROM loan_order WHERE user_id = :userId " +
            "AND tariff_id = :tariffId";

    @Override
    public Order createOrder(Long userId, Long tariffId) {

        String orderId = UUID.randomUUID().toString();

        Random random = new Random();
        Double creditRating = random.nextDouble() * 0.80 + 0.10;

        creditRating = Math.round(creditRating * 100.0) / 100.0;
        String status = "IN_PROGRESS";

        Timestamp timeInsert = new Timestamp(System.currentTimeMillis());

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", orderId)
                .addValue("userId", userId)
                .addValue("tariffId", tariffId)
                .addValue("creditRating", creditRating)
                .addValue("status", status)
                .addValue("timeInsert", timeInsert)
                .addValue("timeUpdate", timeInsert);

        return namedParameterJdbcTemplate.queryForObject(
                INSERT_QUERY,
                parameters,
                new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public Order getOrdersByUserIdAndTariffId(Long userId, Long tariffId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_ORDERS_BY_USERID_QUERY,
                parameters,
                new BeanPropertyRowMapper<>(Order.class)
        );
    }

}
