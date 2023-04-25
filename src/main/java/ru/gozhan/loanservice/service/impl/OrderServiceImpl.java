package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.repository.OrderRepository;
import ru.gozhan.loanservice.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

}
