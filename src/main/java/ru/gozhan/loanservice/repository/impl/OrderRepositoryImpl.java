package ru.gozhan.loanservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

        String INSERT_QUERY = "INSERT INTO loan_order " +
                "(order_id, user_id, tariff_id, credit_rating, status, time_insert, time_update) " +
                "VALUES (:orderId, :userId, :tariffId, :creditRating, :status, :timeInsert, :timeUpdate)";

        namedParameterJdbcTemplate.update(
                INSERT_QUERY,
                parameters);

        SqlParameterSource selectParameters = new MapSqlParameterSource()
                .addValue("orderId", orderId);

        String SELECT_CREATED_ORDER_QUERY = "SELECT * FROM loan_order WHERE order_id = :orderId";

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_CREATED_ORDER_QUERY,
                selectParameters,
                new OrderRowMapper()
        );
    }

    @Override
    public Optional<Order> getOrderByUserIdAndTariffId(Long userId, Long tariffId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("tariffId", tariffId);

        String SELECT_ORDERS_BY_USER_ID_AND_TARIFF_ID_QUERY = "SELECT * FROM loan_order WHERE " +
                "user_id = :userId AND tariff_id = :tariffId";

        List<Order> orders = namedParameterJdbcTemplate.query(
                SELECT_ORDERS_BY_USER_ID_AND_TARIFF_ID_QUERY,
                parameters,
                new OrderRowMapper()
        );

        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    @Override
    public Optional<String> getStatusByOrderId(UUID orderId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", orderId.toString());

        String SELECT_STATUS_BY_ORDER_ID_QUERY = "SELECT status FROM loan_order WHERE order_id = :orderId";

        List<String> results = namedParameterJdbcTemplate.query(
                SELECT_STATUS_BY_ORDER_ID_QUERY,
                parameters,
                (rs, rowNum) -> rs.getString("status")
        );

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public void deleteOrderByUserIdAndOrderId(Long userId, UUID orderId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("orderId", orderId.toString());

        String DELETE_ORDER_BY_USER_ID_AND_ORDER_ID_QUERY = "DELETE FROM loan_order WHERE " +
                "user_id = :userId AND order_id = :orderId";

        int affectedRows = namedParameterJdbcTemplate.update(
                DELETE_ORDER_BY_USER_ID_AND_ORDER_ID_QUERY,
                parameters
        );

    }

    @Override
    public boolean existsByUserIdAndOrderId(Long userId, UUID orderId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("orderId", orderId.toString());

        String EXISTS_BY_USER_ID_AND_ORDER_ID_QUERY = "SELECT EXISTS(SELECT 1 FROM loan_order WHERE " +
                "user_id = :userId AND order_id = :orderId)";

        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(
                EXISTS_BY_USER_ID_AND_ORDER_ID_QUERY,
                parameters,
                Boolean.class
        ));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);

        String SELECT_BY_USER_ID = "SELECT * FROM loan_order WHERE user_id = :userId";

        return namedParameterJdbcTemplate.query(
                SELECT_BY_USER_ID,
                parameters,
                new OrderRowMapper()
        );
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("status", status);

        String SELECT_BY_STATUS = "SELECT * FROM loan_order WHERE status = :status";

        return namedParameterJdbcTemplate.query(
                SELECT_BY_STATUS,
                parameters,
                new OrderRowMapper()
        );
    }

    @Override
    public void updateStatusAndTime(Order order) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("status", order.getStatus())
                .addValue("timeUpdate", order.getTimeUpdate())
                .addValue("id", order.getId());

        String UPDATE_STATUS_AND_TIME_UPDATE_QUERY = "UPDATE loan_order SET status = :status, time_update = :timeUpdate " +
                "WHERE id = :id";

        namedParameterJdbcTemplate.update(
                UPDATE_STATUS_AND_TIME_UPDATE_QUERY,
                parameters
        );
    }

    static class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
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

    }

}
