package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.Order;

public interface OrderRepository {

    boolean createOrder(Order order);

}
