package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order createOrder(Long userId, Long tariffId);

    Optional<Order> getOrderByUserIdAndTariffId(Long userId, Long tariffId);

    Optional<String> getStatusByOrderId(UUID orderId);

    void deleteOrderByUserIdAndOrderId(Long userId, UUID orderId);

    boolean existsByUserIdAndOrderId(Long userId, UUID orderId);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getOrdersByStatus(String status);

    void updateStatusAndTime(Order order);

}
