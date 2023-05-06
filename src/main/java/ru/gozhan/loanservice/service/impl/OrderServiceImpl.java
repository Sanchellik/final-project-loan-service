package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.exception.order.*;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;
import ru.gozhan.loanservice.repository.TariffRepository;
import ru.gozhan.loanservice.service.OrderService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TariffRepository tariffRepository;

    @Override
    public UUID orderProcessing(Long userId, Long tariffId) {

        if (!tariffRepository.existsById(tariffId)) {
            throw new TariffNotFoundException();
        }

        Order order = orderRepository.getOrdersByUserIdAndTariffId(userId, tariffId);
        if (order != null) {
            switch (order.getStatus()) {
                case "IN_PROGRESS" -> {
                    throw new LoanConsiderationException();
                }
                case "APPROVED" -> {
                    throw new LoanAlreadyApprovedException();
                }
                case "REFUSED" -> {
                    throw new TryLaterException();
                }
            }
        }
        Order savedOrder = orderRepository.createOrder(userId, tariffId);

        return savedOrder.getOrderId();
    }

    @Override
    public String getOrderStatus(UUID orderId) {

        String status = orderRepository.getStatusByOrderId(orderId);

        if (status == null) {
            throw new OrderNotFoundException();
        }

        return status;
    }

    @Override
    public boolean deleteOrder(Long userId, UUID orderId) {

        if (!orderRepository.existsByUserIdAndOrderId(userId, orderId)) {
            throw new OrderNotFoundException();
        }

        String status = orderRepository.getStatusByOrderId(orderId);

        if (!"IN_PROGRESS".equals(status)) {
            throw new OrderImpossibleToDeleteException();
        }

        orderRepository.deleteOrderByUserIdAndOrderId(userId, orderId);
        return true;
    }


}
