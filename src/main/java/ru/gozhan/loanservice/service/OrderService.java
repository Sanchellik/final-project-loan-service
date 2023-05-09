package ru.gozhan.loanservice.service;

import ru.gozhan.loanservice.dto.OrderDto;
import ru.gozhan.loanservice.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    UUID createOrder(Long userId, Long tariffId);

    String getOrderStatus(UUID orderId);

    void deleteOrder(Long userId, UUID orderId);

    List<OrderDto> getUserOrders(Long userId);

    List<Order> getOrdersByStatus(String status);

    void updateStatusAndTime(Order order);

}
