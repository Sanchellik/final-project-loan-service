package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.dto.OrderDto;
import ru.gozhan.loanservice.exception.order.*;
import ru.gozhan.loanservice.exception.tariff.TariffNotFoundException;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;
import ru.gozhan.loanservice.repository.TariffRepository;
import ru.gozhan.loanservice.service.OrderService;
import ru.gozhan.loanservice.util.OrderMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TariffRepository tariffRepository;
    private final OrderMapper orderMapper;

    @Override
    public UUID createOrder(Long userId, Long tariffId) {

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
    public void deleteOrder(Long userId, UUID orderId) {

        if (!orderRepository.existsByUserIdAndOrderId(userId, orderId)) {
            throw new OrderNotFoundException();
        }

        String status = orderRepository.getStatusByOrderId(orderId);

        if (!"IN_PROGRESS".equals(status)) {
            throw new OrderImpossibleToDeleteException();
        }

        orderRepository.deleteOrderByUserIdAndOrderId(userId, orderId);
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {

        List<Order> orders = orderRepository.getOrdersByUserId(userId);

        List<OrderDto> orderDtos = orderMapper.toDtoList(orders);

        for (int i = 0; i < orderDtos.size(); i++) {

            Long tariffId = orders.get(i).getTariffId();

            String tariffType = tariffRepository.getTypeById(tariffId)
                    .orElseThrow(() -> new IllegalArgumentException("Tariff type not found for id: " + tariffId));

            orderDtos.get(i).setTariffType(tariffType);
        }

        return orderDtos;
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.getOrdersByStatus(status);
    }

    @Override
    public void updateStatusAndTime(Order order) {
        orderRepository.updateStatusAndTime(order);
    }

}
