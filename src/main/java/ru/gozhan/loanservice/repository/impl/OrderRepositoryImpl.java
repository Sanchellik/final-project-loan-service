package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String INSERT_QUERY = "INSERT INTO loan_order " +
            "(order_id, user_id, tariff_id, credit_rating, status, time_insert, time_update) " +
            "VALUES (:orderId, :userId, :tariffId, :creditRating, :status, :timeInsert, :timeUpdate)";

    private final String SELECT_ORDERS_BY_USER_ID_AND_TARIFF_ID_QUERY = "SELECT * FROM loan_order WHERE " +
            "user_id = :userId AND tariff_id = :tariffId";

    private final String SELECT_CREATED_ORDER_QUERY = "SELECT * FROM loan_order WHERE order_id = :orderId";

    private final String SELECT_STATUS_BY_ORDER_ID_QUERY = "SELECT status FROM loan_order WHERE order_id = :orderId";

    private final String DELETE_ORDER_BY_USER_ID_AND_ORDER_ID_QUERY = "DELETE FROM loan_order WHERE " +
            "user_id = :userId AND order_id = :orderId";

    private final String EXISTS_BY_USER_ID_AND_ORDER_ID_QUERY = "SELECT EXISTS(SELECT 1 FROM loan_order WHERE " +
            "user_id = :userId AND order_id = :orderId)";

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

        namedParameterJdbcTemplate.update(
                INSERT_QUERY,
                parameters);

        SqlParameterSource selectParameters = new MapSqlParameterSource()
                .addValue("orderId", orderId);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_CREATED_ORDER_QUERY,
                selectParameters,
//                new BeanPropertyRowMapper<>(Order.class)
                (rs, rowNum) -> {
                    Order order = new Order();
                    order.setId(rs.getLong("id"));
                    order.setOrderId(UUID.fromString(rs.getString("order_id")));
                    order.setUserId(rs.getLong("user_id"));
                    order.setTariffId(rs.getLong("tariff_id"));
                    order.setCreditRating(rs.getDouble("credit_rating"));
                    order.setStatus(rs.getString("status"));
                    order.setTimeInsert(rs.getTimestamp("time_insert"));
                    order.setTimeUpdate(rs.getTimestamp("time_update"));
                    return order;
                }
        );
    }

    @Override
    public Order getOrdersByUserIdAndTariffId(Long userId, Long tariffId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("tariffId", tariffId);

        List<Order> orders = namedParameterJdbcTemplate.query(
                SELECT_ORDERS_BY_USER_ID_AND_TARIFF_ID_QUERY,
                parameters,
//                new BeanPropertyRowMapper<>(Order.class)
                (rs, rowNum) -> {
                    Order order = new Order();
                    order.setId(rs.getLong("id"));
                    order.setOrderId(UUID.fromString(rs.getString("order_id")));
                    order.setUserId(rs.getLong("user_id"));
                    order.setTariffId(rs.getLong("tariff_id"));
                    order.setCreditRating(rs.getDouble("credit_rating"));
                    order.setStatus(rs.getString("status"));
                    order.setTimeInsert(rs.getTimestamp("time_insert"));
                    order.setTimeUpdate(rs.getTimestamp("time_update"));
                    return order;
                }
        );

        return orders.isEmpty() ? null : orders.get(0);
    }

    @Override
    public String getStatusByOrderId(UUID orderId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", orderId.toString());

        List<String> results = namedParameterJdbcTemplate.query(
                SELECT_STATUS_BY_ORDER_ID_QUERY,
                parameters,
                (rs, rowNum) -> rs.getString("status")
        );

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public boolean deleteOrderByUserIdAndOrderId(Long userId, UUID orderId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("orderId", orderId.toString());

        int affectedRows = namedParameterJdbcTemplate.update(
                DELETE_ORDER_BY_USER_ID_AND_ORDER_ID_QUERY,
                parameters
        );

        return affectedRows > 0;
    }

    @Override
    public boolean existsByUserIdAndOrderId(Long userId, UUID orderId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("orderId", orderId.toString());

        return namedParameterJdbcTemplate.queryForObject(
                EXISTS_BY_USER_ID_AND_ORDER_ID_QUERY,
                parameters,
                Boolean.class
        );
    }

}
