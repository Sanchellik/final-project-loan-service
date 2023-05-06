package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Order;

import java.util.UUID;

public interface OrderRepository {

    Order createOrder(Long userId, Long tariffId);

    Order getOrdersByUserIdAndTariffId(Long userId, Long tariffId);

    String getStatusByOrderId(UUID orderId);

    boolean deleteOrderByUserIdAndOrderId(Long userId, UUID orderId);

    boolean existsByUserIdAndOrderId(Long userId, UUID orderId);

}
