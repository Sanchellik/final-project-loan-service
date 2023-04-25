package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String INSERT_QUERY = "INSERT INTO loan_order (order_id, user_id, tariff_id, credit_rating, status) " +
            "VALUES (:orderId, :userId, :tariffId, :creditRating, :status)";

    @Override
    public boolean createOrder(Order order) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", order.getOrderId())
                .addValue("userId", order.getUserId())
                .addValue("tariffId", order.getTariffId())
                .addValue("creditRating", order.getCreditRating())
                .addValue("status", order.getStatus());
        return namedParameterJdbcTemplate.update(INSERT_QUERY, parameters) > 0;
    }

}
