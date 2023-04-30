package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.model.Order;
import ru.gozhan.loanservice.repository.OrderRepository;
import ru.gozhan.loanservice.repository.TariffRepository;
import ru.gozhan.loanservice.repository.UserRepository;
import ru.gozhan.loanservice.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TariffRepository tariffRepository;

    @Override
    public Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.getUserIdByUsername(username);
    }

//    @Override
//    public String tariffProcessing(Long tariffId) {
//        if (!tariffRepository.existsById(tariffId)) {
//            return "tariff-not-found";
//        }
//
//        Order order = orderRepository.getOrdersByUserIdAndTariffId(getUserId(), tariffId);
//        if (order == null) {
//            orderRepository.createOrder(getUserId(), tariffId);
//        } else {
//            switch (order.getStatus()) {
//                case "IN_PROGRESS"
//            }
//        }
//
//    }

}
