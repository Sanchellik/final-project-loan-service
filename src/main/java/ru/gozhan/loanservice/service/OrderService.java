package ru.gozhan.loanservice.service;

import ru.gozhan.loanservice.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    UUID orderProcessing(Long userId, Long tariffId);

    String getOrderStatus(UUID orderId);

    void deleteOrder(Long userId, UUID orderId);

    List<OrderDto> getUserOrders(Long userId);

}
