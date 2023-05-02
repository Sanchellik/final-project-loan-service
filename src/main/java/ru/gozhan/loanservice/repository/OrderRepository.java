package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Order;

public interface OrderRepository {

    Order createOrder(Long userId, Long tariffId);

    Order getOrdersByUserIdAndTariffId(Long userId, Long tariffId);

}
