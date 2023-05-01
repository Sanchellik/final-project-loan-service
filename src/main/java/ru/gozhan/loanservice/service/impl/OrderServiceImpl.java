package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.exception.order.LoanAlreadyApprovedException;
import ru.gozhan.loanservice.exception.order.LoanConsiderationException;
import ru.gozhan.loanservice.exception.order.TryLaterException;
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
    //    private final UserRepository userRepository;
    private final TariffRepository tariffRepository;

//    @Override
//    public Long getUserId() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        return userRepository.getUserIdByUsername(username);
//    }

    @Override
    public UUID orderProcessing(Long userId, Long tariffId) throws
            TariffNotFoundException, LoanConsiderationException,
            LoanAlreadyApprovedException, TryLaterException {

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

}
