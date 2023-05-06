package ru.gozhan.loanservice.service;

import java.util.UUID;

public interface OrderService {

    UUID orderProcessing(Long userId, Long tariffId);

    String getOrderStatus(UUID orderId);

}
